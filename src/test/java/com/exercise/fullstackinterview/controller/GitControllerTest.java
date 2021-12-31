package com.exercise.fullstackinterview.controller;

import com.exercise.fullstackinterview.dto.BranchDto;
import com.exercise.fullstackinterview.service.GitService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest
class GitControllerTest {

  @Autowired
  WebTestClient webTestClient;

  @MockBean
  GitService gitService;

  @Test
  void getAllBranches() {
    BranchDto branchDto = BranchDto.builder().name("b1").build();
    List<BranchDto> branchDtos = List.of(branchDto);

    Flux<BranchDto> branchDtoFlux = Flux.fromIterable(branchDtos);

    Mockito.when(gitService.getAllBranches("cheo2322", "fullstack-interview-test",
            "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"))
        .thenReturn(branchDtoFlux);

    webTestClient.get()
        .uri("/git-wrapper/branches?user=cheo2322&repo=fullstack-interview-test")
        .header("token", "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")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(BranchDto.class);

    Mockito.verify(gitService, Mockito.times(1))
        .getAllBranches("cheo2322", "fullstack-interview-test",
            "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");
  }

  @Test
  void getCommits() {
    BranchDto branchDto = BranchDto.builder().name("b1").build();
    List<BranchDto> branchDtos = List.of(branchDto);

    Flux<BranchDto> branchDtoFlux = Flux.fromIterable(branchDtos);

    Mockito.when(gitService.getAllBranches("cheo2322", "fullstack-interview-test",
            "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"))
        .thenReturn(branchDtoFlux);

    webTestClient.get()
        .uri("/git-wrapper/branches?user=cheo2322&repo=fullstack-interview-test")
        .header("token", "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")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(BranchDto.class);

    Mockito.verify(gitService, Mockito.times(1))
        .getAllBranches("cheo2322", "fullstack-interview-test",
            "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");
  }
}