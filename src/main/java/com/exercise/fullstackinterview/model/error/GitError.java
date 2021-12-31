package com.exercise.fullstackinterview.model.error;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class GitError {

  private String message;
  @JsonProperty("documentation_url")
  private String documentationUrl;
}
