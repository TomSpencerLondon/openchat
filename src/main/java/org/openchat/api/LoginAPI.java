package org.openchat.api;

import static org.eclipse.jetty.http.HttpStatus.NOT_FOUND_404;
import static org.eclipse.jetty.http.HttpStatus.OK_200;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import java.util.Optional;
import org.openchat.api.domain.users.UserCredentials;
import org.openchat.domain.users.User;
import org.openchat.domain.users.UserRepository;
import org.openchat.json.UserJson;
import spark.Request;
import spark.Response;

public class LoginAPI {

  private UserRepository userRepository;

  public LoginAPI(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String login(Request request, Response response) {
    UserCredentials credentials = credentialsFrom(request);
    Optional<User> user = userRepository.userFor(credentials);
    if (user.isPresent()) {
      response.status(OK_200);
      response.type("application/json");
      return UserJson.jsonFor(user.get());
    }
    response.status(NOT_FOUND_404);
    return "Invalid credentials.";
  }

  private UserCredentials credentialsFrom(Request req) {
    JsonObject json = Json.parse(req.body()).asObject();
    return new UserCredentials(
        json.getString("username", ""),
        json.getString("password", "")
    );
  }
}
