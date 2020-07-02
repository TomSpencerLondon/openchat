package org.openchat.api;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;
import static org.openchat.api.infrastructure.json.PostJson.toJson;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.domain.posts.InappropriateLanguageException;
import org.openchat.domain.posts.Post;
import org.openchat.domain.posts.PostService;
import spark.Request;
import spark.Response;

public class PostsAPI {

  private PostService postService;

  public PostsAPI(PostService postService) {
    this.postService = postService;
  }

  public String createPost(Request request, Response response) {
    String userId = request.params("userId");
    String text = postTextFrom(request);

    try {
      Post post = postService.createPost(userId, text);
      return prepareOKResponse(response, post);
    } catch (InappropriateLanguageException e) {
      return prepareErrorResponse(response);
    }
  }

  private String prepareErrorResponse(Response response) {
    response.status(BAD_REQUEST_400);
    return "Post contains inappropriate language.";
  }

  private String prepareOKResponse(Response response, Post post) {
    response.status(CREATED_201);
    response.type("application/json");
    return toJson(post);
  }

  private String postTextFrom(Request req) {
    JsonObject json = Json.parse(req.body()).asObject();
    return json.getString("text", "");
  }

}
