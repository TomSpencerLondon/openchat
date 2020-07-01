package org.openchat.api;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.domain.posts.PostService;
import spark.Request;
import spark.Response;

public class PostsAPI {

  private PostService postService;

  public PostsAPI(PostService postService) {
    this.postService = postService;
  }

  public String createPost(Request req, Response res) {
    String userId = req.params("userId");
    String text = postTextFrom(req);
    postService.createPost(userId, text);
    return "";
  }

  private String postTextFrom(Request req) {
    JsonObject json = Json.parse(req.body()).asObject();
    return json.getString("text", "");
  }
}
