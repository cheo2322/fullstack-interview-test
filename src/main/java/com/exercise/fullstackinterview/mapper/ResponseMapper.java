package com.exercise.fullstackinterview.mapper;

import com.exercise.fullstackinterview.dto.CommitDto;
import com.exercise.fullstackinterview.dto.PullRequestDto;
import com.exercise.fullstackinterview.dto.PullRequestDto.Status;
import com.exercise.fullstackinterview.dto.SimpleCommitDto;
import com.exercise.fullstackinterview.model.commit.CommitResponse;
import com.exercise.fullstackinterview.model.commit.File;
import com.exercise.fullstackinterview.model.pullrequest.PullRequest;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResponseMapper {

  @Mapping(source = "commit.message", target = "message")
  @Mapping(source = "commit.author.name", target = "author")
  @Mapping(source = "commit.author.date", target = "timestamp")
  @Mapping(source = "commit.author.email", target = "email")
  @Mapping(source = "files", target = "filesChanged", qualifiedByName = "numberOfFiles")
  CommitDto commitToDto(CommitResponse response);

  @Named("numberOfFiles")
  static int numberOfFiles(List<File> files) {
    return files.size();
  }

  @Mapping(source = "sha", target = "sha")
  @Mapping(source = "commit.message", target = "message")
  @Mapping(source = "commit.author.name", target = "author")
  @Mapping(source = "commit.author.date", target = "timestamp")
  SimpleCommitDto commitToSimple(CommitResponse commitResponse);

  @Mapping(source = "user.login", target = "author")
  @Mapping(source = "title", target = "title")
  @Mapping(source = "body", target = "description")
  @Mapping(source = "state", target = "status", qualifiedByName = "getStatus")
  @Mapping(source = "number", target = "number")
  PullRequestDto pullRequestToDto(PullRequest pullRequest);

  @Named("getStatus")
  static Status getStatus(String state) {
    if (state.equals("open")) {
      return Status.OPEN;
    } else if (state.equals("closed")) {
      return Status.CLOSED;
    } else {
      return Status.MERGED;
    }
  }
}
