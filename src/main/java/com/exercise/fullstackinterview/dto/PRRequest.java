package com.exercise.fullstackinterview.dto;

import com.exercise.fullstackinterview.dto.PullRequestDto.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PRRequest {

  private String title;
  private String body;
  private String head;
  private String base;
  private Status status;
}
