package com.exercise.fullstackinterview.model.pullrequest;

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
public class Base {

  private String label;
  private String ref;
  private String sha;
  private User user;
  private Repo repo;
}
