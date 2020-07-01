package org.openchat.api;

import static org.eclipse.jetty.http.HttpStatus.CREATED_201;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import java.time.format.DateTimeFormatter;
import org.openchat.domain.posts.Post;
import org.openchat.domain.posts.PostService;
import spark.Request;
import spark.Response;

public class PostsAPI {

  private PostService postService;
  private Response response;

  public PostsAPI(PostService postService, Response response) {
    this.postService = postService;
    this.response = response;
  }

  public String createPost(Request req, Response res) {
    String userId = req.params("userId");
    String text = postTextFrom(req);
    Post post = postService.createPost(userId, text);
    response.status(CREATED_201);
    response.type("application/json");
    return PostJson.toJson(post);
  }

  private String postTextFrom(Request req) {
    JsonObject json = Json.parse(req.body()).asObject();
    return json.getString("text", "");
  }

  private static class PostJson {
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
}
