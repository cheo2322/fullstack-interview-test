package com.exercise.fullstackinterview.service;

import com.exercise.fullstackinterview.dto.BranchDto;
import com.exercise.fullstackinterview.dto.CommitDto;
import com.exercise.fullstackinterview.dto.PullRequestDto;
import com.exercise.fullstackinterview.dto.SimpleCommitDto;
import com.exercise.fullstackinterview.mapper.ResponseMapper;
import com.exercise.fullstackinterview.webclient.GitWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

  public Flux<SimpleCommitDto> get(String branch, String user, String repo, String token) {
    return gitWebClient.getCommit(branch, user, repo, token)
        .expand(response -> {
          if (response.getParents().isEmpty()) {
            return Mono.empty();
          }

          return gitWebClient.getCommit(response.getParents().get(0).getSha(), user, repo, token);
        }).map(response -> responseMapper.commitToSimple(response));

  }

  public Mono<CommitDto> getCommit(String sha, String user, String repo, String token) {
    return gitWebClient.getCommit(sha, user, repo, token)
        .map(response -> responseMapper.commitToDto(response));
  }

  public Flux<PullRequestDto> getPullRequestDto(String user, String repo, String token) {
    return gitWebClient.getPullRequest(user, repo, token, "")
        .expand(pullRequest -> {
          if (pullRequest.getNumber() <= 1) {
            return Mono.empty();
          }

          return gitWebClient.getPullRequest(user, repo, token, "/".concat(
              String.valueOf(pullRequest.getNumber() - 1)));
        }).map(pullRequest -> responseMapper.pullRequestToDto(pullRequest));
  }
}
