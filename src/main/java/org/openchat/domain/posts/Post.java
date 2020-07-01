package org.openchat.domain.posts;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

import java.time.LocalDateTime;

public class Post {

  private final String postId;
  private final String userId;
  private final String postText;
  private final LocalDateTime dateTime;

  public Post(String postId, String userId, String postText, LocalDateTime dateTime) {

    this.postId = postId;
    this.userId = userId;
    this.postText = postText;
    this.dateTime = dateTime;
  }

  public String postId() {
    return postId;
  }

  public String userId() {
    return userId;
  }

  public String text() {
    return postText;
  }

  public LocalDateTime dateTime() {
    return dateTime;
  }

  @Override
  public boolean equals(Object other) {
    return reflectionEquals(this, other);
  }

  @Override
  public int hashCode() {
    return reflectionHashCode(this);
  }
}
