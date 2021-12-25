package com.exercise.fullstackinterview.controller;

import com.exercise.fullstackinterview.dto.CommitDto;
import com.exercise.fullstackinterview.model.branches.Branch;
import com.exercise.fullstackinterview.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
  public Flux<Branch> getBranches() {
    return gitService.getBranches();
  }

  @GetMapping("/commits")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public Mono<CommitDto> getCommits(@RequestParam String branch) {
    return gitService.getCommits(branch);
  }
}
