package org.openchat.api.domain.users;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.openchat.domain.users.User;

public class UserCredentials {

  private final String username;
  private final String password;

  public UserCredentials(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserCredentials that = (UserCredentials) o;

    return new EqualsBuilder()
        .append(username, that.username)
        .append(password, that.password)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(username)
        .append(password)
        .toHashCode();
  }

  public boolean matches(User user) {
    return username.equals(user.username()) && password.equals(user.password());
  }
}
