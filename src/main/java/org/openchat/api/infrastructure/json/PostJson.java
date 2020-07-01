package org.openchat.api.infrastructure.json;

import com.eclipsesource.json.JsonObject;
import java.time.format.DateTimeFormatter;
import org.openchat.domain.posts.Post;

public class PostJson {
  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
  public static String toJson(Post post) {
    return new JsonObject()
                  .add("postId", post.postId())
                  .add("userId", post.userId())
                  .add("text", post.text())
                  .add("dateTime", formatter.format(post.dateTime()))
                  .toString();
  }
}
