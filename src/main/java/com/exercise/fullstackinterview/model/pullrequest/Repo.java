package com.exercise.fullstackinterview.model.pullrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Repo {

  private int id;
  private String node_id;
  private String name;
  private String full_name;
  @JsonProperty("private")
  private boolean isPrivate;
  private Owner owner;
  private String html_url;
  private String description;
  private boolean fork;
  private String url;
  private String forks_url;
  private String keys_url;
  private String collaborators_url;
  private String teams_url;
  private String hooks_url;
  private String issue_events_url;
  private String events_url;
  private String assignees_url;
  private String branches_url;
  private String tags_url;
  private String blobs_url;
  private String git_tags_url;
  private String git_refs_url;
  private String trees_url;
  private String statuses_url;
  private String languages_url;
  private String stargazers_url;
  private String contributors_url;
  private String subscribers_url;
  private String subscription_url;
  private String commits_url;
  private String git_commits_url;
  private String comments_url;
  private String issue_comment_url;
  private String contents_url;
  private String compare_url;
  private String merges_url;
  private String archive_url;
  private String downloads_url;
  private String issues_url;
  private String pulls_url;
  private String milestones_url;
  private String notifications_url;
  private String labels_url;
  private String releases_url;
  private String deployments_url;
  private ZonedDateTime created_at;
  private ZonedDateTime updated_at;
  private ZonedDateTime pushed_at;
  private String git_url;
  private String ssh_url;
  private String clone_url;
  private String svn_url;
  private Object homepage;
  private int size;
  private int stargazers_count;
  private int watchers_count;
  private Object language;
  private boolean has_issues;
  private boolean has_projects;
  private boolean has_downloads;
  private boolean has_wiki;
  private boolean has_pages;
  private int forks_count;
  private Object mirror_url;
  private boolean archived;
  private boolean disabled;
  private int open_issues_count;
  private Object license;
  private boolean allow_forking;
  private boolean is_template;
  private List<Object> topics;
  private String visibility;
  private int forks;
  private int open_issues;
  private int watchers;
  private String default_branch;
}
