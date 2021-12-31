package com.exercise.fullstackinterview.model.pullrequest;

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
public class Links {

  private HRef self;
  private HRef html;
  private HRef issue;
  private HRef comments;
  @JsonProperty("review_comments")
  private HRef reviewComments;
  @JsonProperty("review_comment")
  private HRef reviewComment;
  private HRef commits;
  private HRef statuses;
}
