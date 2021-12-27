package com.exercise.fullstackinterview.controller;

import com.exercise.fullstackinterview.dto.BranchDto;
import com.exercise.fullstackinterview.dto.CommitDto;
import com.exercise.fullstackinterview.dto.PullRequestDto;
import com.exercise.fullstackinterview.dto.SimpleCommitDto;
import com.exercise.fullstackinterview.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/git-wrapper")
public class GitController {

  @Autowired
  GitService gitService;

  @GetMapping("/branches")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public Flux<BranchDto> getAllBranches(@RequestParam String user, @RequestParam String repo,
      @RequestHeader String token) {
    return gitService.getAllBranches(user, repo, token);
  }

  @GetMapping("/commits")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public Flux<SimpleCommitDto> getCommits(@RequestParam String branch, @RequestParam String user,
      @RequestParam String repo, @RequestHeader String token) {
    return gitService.get(branch, user, repo, token);
  }

  @GetMapping("/commits/{sha}")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public Mono<CommitDto> getCommit(@PathVariable String sha, @RequestParam String user,
      @RequestParam String repo, @RequestHeader String token) {
    return gitService.getCommit(sha, user, repo, token);
  }

  @GetMapping("/pulls")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public Flux<PullRequestDto> getPulls(@RequestParam String user, @RequestParam String repo,
      @RequestHeader String token) {
    return gitService.getPullRequestDto(user, repo, token);
  }
}
