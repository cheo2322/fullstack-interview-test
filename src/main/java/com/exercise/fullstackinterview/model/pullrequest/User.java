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
public class User {

  private String login;
  private int id;
  private String node_id;
  private String avatar_url;
  private String gravatar_id;
  private String url;
  private String html_url;
  private String followers_url;
  private String following_url;
  private String gists_url;
  private String starred_url;
  private String subscriptions_url;
  private String organizations_url;
  private String repos_url;
  private String events_url;
  private String received_events_url;
  private String type;
  private boolean site_admin;
}
