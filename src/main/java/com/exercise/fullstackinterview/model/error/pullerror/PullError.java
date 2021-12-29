package com.exercise.fullstackinterview.model.error.pullerror;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
public class PullError {

  private String message;
  private List<PullResourceError> errors;
  @JsonProperty("documentation_url")
  private String documentationUrl;
}
