package com.exercise.fullstackinterview.model.pullrequest;

import java.time.ZonedDateTime;
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
public class PullRequest {

  private String url;
  private int id;
  private String node_id;
  private String html_url;
  private String diff_url;
  private String patch_url;
  private String issue_url;
  private int number;
  private String state;
  private boolean locked;
  private String title;
  private User user;
  private Object body;
  private ZonedDateTime created_at;
  private ZonedDateTime updated_at;
  private Object closed_at;
  private Object merged_at;
  private String merge_commit_sha;
  private Object assignee;
  private List<Object> assignees;
  private List<Object> requested_reviewers;
  private List<Object> requested_teams;
  private List<Object> labels;
  private Object milestone;
  private boolean draft;
  private String commits_url;
  private String review_comments_url;
  private String review_comment_url;
  private String comments_url;
  private String statuses_url;
  private Head head;
  private Base base;
  private Links _links;
  private String author_association;
  private Object auto_merge;
  private Object active_lock_reason;
}
