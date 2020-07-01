package org.openchat.api;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.api.infrastructure.json.PostJson;
import org.openchat.domain.posts.InappropriateLanguageException;
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

    try {
      Post post = postService.createPost(userId, text);
      return prepareOKResponse(post);
    } catch (InappropriateLanguageException e) {
      return prepareErrorResponse();
    }
  }

  private String prepareErrorResponse() {
    response.status(BAD_REQUEST_400);
    return "Post contains inappropriate language.";
  }

  private String prepareOKResponse(Post post) {
    response.status(CREATED_201);
    response.type("application/json");
    return PostJson.toJson(post);
  }

  private String postTextFrom(Request req) {
    JsonObject json = Json.parse(req.body()).asObject();
    return json.getString("text", "");
  }

}
