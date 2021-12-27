package com.exercise.fullstackinterview.service;

import com.exercise.fullstackinterview.dto.BranchDto;
import com.exercise.fullstackinterview.dto.CommitDto;
import com.exercise.fullstackinterview.dto.PullRequestDto;
import com.exercise.fullstackinterview.dto.SimpleCommitDto;
import com.exercise.fullstackinterview.mapper.ResponseMapper;
import com.exercise.fullstackinterview.model.commit.CommitResponse;
import com.exercise.fullstackinterview.model.commit.Parent;
import com.exercise.fullstackinterview.webclient.GitWebClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GitService {

  @Autowired
  GitWebClient gitWebClient;

  @Autowired
  ResponseMapper responseMapper;

  public Flux<BranchDto> getAllBranches(String user, String repo, String token) {
    return gitWebClient.getAllBranches(user, repo, token)
        .map(branch -> BranchDto.builder().name(branch.name).build());
  }

  public List<SimpleCommitDto> getCommits(String branch, String token) {
    Parent[] parents = {Parent.builder().sha(branch).build()};

    List<SimpleCommitDto> commits = new ArrayList<>();
    getCommitResponse(CommitResponse.builder().parents(Arrays.asList(parents)).build(), commits,
        token);

    return commits;
  }

  public Mono<CommitDto> getCommit(String sha, String user, String repo, String token) {
    return gitWebClient.getCommit(sha, user, repo, token)
        .map(response -> responseMapper.commitToDto(response));
  }

  public Flux<PullRequestDto> getPullRequestDto(String user, String repo, String token) {
    return gitWebClient.getPullRequest(user, repo, token, "")
        .expand(pullRequest -> {
          if (pullRequest.getNumber() <= 1) {
            return Flux.just();
          }

          return gitWebClient.getPullRequest(user, repo, token, "/".concat(
              String.valueOf(pullRequest.getNumber() - 1)));
        })
        .map(pullRequest -> responseMapper.pullRequestToDto(pullRequest));
  }

  private void getCommitResponse(CommitResponse commitResponse, List<SimpleCommitDto> commits,
      String token) {
    if (commitResponse.getParents().isEmpty()) {
      return;
    }

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);

    HttpEntity<Void> entity = new HttpEntity<>(headers);
    String url = "https://api.github.com/repos/cheo2322/fullstack-interview-test/commits/";

    CommitResponse response = restTemplate.exchange(
        url.concat(commitResponse.getParents().get(0).getSha()), HttpMethod.GET, entity,
        CommitResponse.class).getBody();

    commits.add(responseMapper.commitToSimple(response));

    getCommitResponse(Objects.requireNonNull(response), commits, token);
  }
}
