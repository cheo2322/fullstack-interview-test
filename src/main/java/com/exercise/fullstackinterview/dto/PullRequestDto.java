package com.exercise.fullstackinterview.dto;

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
public class PullRequestDto {

  enum Status {
    OPEN,
    CLOSED,
    MERGED
  }

  private String author;
  private String title;
  private String description;
  private Status status;
}
