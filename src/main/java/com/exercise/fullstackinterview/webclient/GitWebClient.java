package com.exercise.fullstackinterview.webclient;

import com.exercise.fullstackinterview.dto.MergeDto;
import com.exercise.fullstackinterview.dto.PRRequest;
import com.exercise.fullstackinterview.dto.PullRequestResponse;
import com.exercise.fullstackinterview.model.branches.Branch;
import com.exercise.fullstackinterview.model.commit.CommitResponse;
import com.exercise.fullstackinterview.model.error.GitError;
import com.exercise.fullstackinterview.model.error.pullerror.PullError;
import com.exercise.fullstackinterview.model.pullrequest.PullRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
public class GitWebClient {

  private final WebClient webClient;

  protected GitWebClient() {
    this.webClient = WebClient.builder()
        .baseUrl("https://api.github.com")
        .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection().compress(true)))
        .build();
  }

  protected WebClient.RequestBodySpec setUpGetWebClient(String uri, String user, String repo,
      String token) {
    return (RequestBodySpec) this.webClient
        .get()
        .uri(uriBuilder -> uriBuilder.path("/repos/{user}/{repo}".concat(uri))
            .queryParam("state", "all")
            .build(user, repo))
        .headers(httpHeaders -> httpHeaders.setBearerAuth(token));
  }

  public Flux<Branch> getAllBranches(String user, String repo, String token) {
    return get("/branches", user, repo, token)
        .bodyToFlux(Branch.class);
  }

  public Mono<CommitResponse> getCommit(String branch, String user, String repo, String token) {
    return get("/commits/".concat(branch), user, repo, token)
        .bodyToMono(CommitResponse.class);
  }

  public Flux<PullRequest> getAllPullRequests(String user, String repo, String token) {
    return get("/pulls", user, repo, token)
        .bodyToFlux(PullRequest.class);
  }

  public Mono<PullRequest> getPullRequest(String user, String repo, String token, int number) {
    return get("/pulls".concat("/").concat(String.valueOf(number)), user, repo, token)
        .bodyToMono(PullRequest.class);
  }

  public Mono<MergeDto> mergePull(String user, String repo, String token, int pullNumber) {
    return this.webClient
        .put()
        .uri(uriBuilder -> uriBuilder.path("/repos/{user}/{repo}/pulls/{pullNumber}/merge")
            .build(user, repo, pullNumber))
        .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
        .retrieve()
        .onStatus(HttpStatus::isError, clientResponse -> clientResponse.bodyToMono(GitError.class)
            .flatMap(gitError -> Mono.error(
                new ResponseStatusException(clientResponse.statusCode(), gitError.getMessage()))))
        .bodyToMono(MergeDto.class);
  }

  public Mono<PullRequestResponse> createPull(PRRequest pullRequestDto, String user,
      String repo, String token) {

    return this.webClient
        .post()
        .uri(uriBuilder -> uriBuilder.path("/repos/{user}/{repo}/pulls").build(user, repo))
        .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
        .body(Mono.just(pullRequestDto), PRRequest.class)
        .retrieve()
        .onStatus(HttpStatus::isError, clientResponse -> {
          if (clientResponse.statusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
            return clientResponse.bodyToMono(PullError.class)
                .flatMap(pullError ->
                    Mono.error(new ResponseStatusException(clientResponse.statusCode(),
                        pullError.getErrors().get(0).getMessage())));
          }

          return clientResponse.bodyToMono(GitError.class)
              .flatMap(gitError ->
                  Mono.error(new ResponseStatusException(clientResponse.statusCode(),
                      gitError.getMessage())));
        })
        .bodyToMono(PullRequestResponse.class);
  }

  private ResponseSpec get(String uri, String user, String repo, String token) {
    return setUpGetWebClient(uri, user, repo, token)
        .retrieve()
        .onStatus(HttpStatus::isError, clientResponse -> clientResponse.bodyToMono(GitError.class)
            .flatMap(errorBody ->
                Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    errorBody.getMessage())))
        );
  }
}
