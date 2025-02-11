package com.exercise.fullstackinterview.service;

import com.exercise.fullstackinterview.dto.BranchDto;
import com.exercise.fullstackinterview.dto.CommitDto;
import com.exercise.fullstackinterview.dto.MergeDto;
import com.exercise.fullstackinterview.dto.PRRequest;
import com.exercise.fullstackinterview.dto.PullRequestDto;
import com.exercise.fullstackinterview.dto.PullRequestDto.Status;
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

  public Flux<SimpleCommitDto> getCommits(String branch, String user, String repo, String token) {
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
    return gitWebClient.getAllPullRequests(user, repo, token)
        .map(pullRequest -> responseMapper.pullRequestToDto(pullRequest));
  }

  public Mono<MergeDto> createPull(PRRequest pullRequest, String user, String repo, String token) {
    return gitWebClient.createPull(pullRequest, user, repo, token).map(pullRequestResponse -> {
          if (pullRequest.getStatus().equals(Status.MERGED)) {
            return gitWebClient.mergePull(user, repo, token, pullRequestResponse.getNumber());
          }

          return Mono.just(MergeDto.builder()
              .sha(String.valueOf(pullRequestResponse.getId()))
              .message(Status.OPEN.toString())
              .merged(false)
              .build());
        }
    ).flatMap(mergeDtoMono -> mergeDtoMono);
  }
}
