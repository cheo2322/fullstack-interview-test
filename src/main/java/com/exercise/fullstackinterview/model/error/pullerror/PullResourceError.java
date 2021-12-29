package com.exercise.fullstackinterview.model.error.pullerror;

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
public class PullResourceError {

  private String resource;
  private String code;
  private String message;
}
