package org.openchat.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.eclipsesource.json.JsonObject;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openchat.domain.posts.Post;
import org.openchat.domain.posts.PostService;
import spark.Request;
import spark.Response;

@RunWith(MockitoJUnitRunner.class)
public class PostsAPIShould {

  private static final String POST_ID = UUID.randomUUID().toString();
  private static final String USER_ID = UUID.randomUUID().toString();
  private static final String POST_TEXT = "Some text";
  private static final LocalDateTime DATE_TIME = LocalDateTime.of(2018, 1, 10, 14, 30, 0);
  private static final Post POST = new Post(POST_ID, USER_ID, POST_TEXT, DATE_TIME);
  private PostsAPI postsAPI;

  @Mock
  private Request request;
  @Mock
  private Response response;
  @Mock
  private PostService postService;

  @Before
  public void setUp() throws Exception {
    postsAPI = new PostsAPI(postService, response);
    given(request.params("userId")).willReturn(USER_ID);
    given(request.body()).willReturn(jsonContaining(POST_TEXT));
    given(postService.createPost(USER_ID, POST_TEXT)).willReturn(POST);
  }

  @Test
  public void create_a_post() {
    postsAPI.createPost(request, response);
    verify(postService).createPost(USER_ID, POST_TEXT);
  }

  @Test
  public void return_a_json_representing_a_newly_created_post() {
    String result = postsAPI.createPost(request, response);

    verify(response).status(201);
    verify(response).type("application/json");
    assertThat(result).isEqualTo(jsonContaining(POST));
  }

  private String jsonContaining(Post post) {
    return new JsonObject()
                .add("postId", post.postId())
                .add("userId", post.userId())
                .add("text", post.text())
                .add("dateTime", "2018-01-10T14:30:00Z")
                .toString();
  }

  private String jsonContaining(String text) {
    return new JsonObject().add("text", text).toString();
  }
}
