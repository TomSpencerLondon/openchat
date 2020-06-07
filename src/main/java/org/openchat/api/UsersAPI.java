package org.openchat.api;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;
import static org.openchat.json.UserJson.jsonFor;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.domain.users.RegistrationData;
import org.openchat.domain.users.User;
import org.openchat.domain.users.UserService;
import org.openchat.domain.users.UsernameAlreadyInUseException;
import org.openchat.json.UserJson;
import spark.Request;
import spark.Response;

public class UsersAPI {

  private UserService userService;

  public UsersAPI(UserService userService) {
    this.userService = userService;
  }

  public String createUser(Request request, Response response) {
    try {
      RegistrationData registration = registrationDataFrom(request);

      User user = userService.createUser(registration);
      response.status(CREATED_201);
      response.type("application/json");
      return jsonFor(user);
    } catch (UsernameAlreadyInUseException e) {
      response.status(BAD_REQUEST_400);
      return "Username already in use.";
    }
  }

  private RegistrationData registrationDataFrom(Request request) {
    JsonObject json = Json.parse(request.body()).asObject();
    return new RegistrationData(
        json.getString("username", ""),
        json.getString("password", ""),
        json.getString("about", "")
    );
  }
}
