package org.openchat.domain.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.openchat.api.domain.users.UserCredentials;

public class UserRepository {

  private List<User> users = new ArrayList<>();

  public void add(User user) {
    users.add(user);
  }

  public Boolean isUsernameTaken(String username) {
    return users.stream()
                .anyMatch(user -> user.username().equals(username));
  }

  public Optional<User> userFor(UserCredentials userCredentials) {
    throw new UnsupportedOperationException("Implement me!");
  }
}
