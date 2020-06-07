package org.openchat.domain.users;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RegistrationData {

  private final String username;
  private final String password;
  private final String about;

  public RegistrationData(String username, String password, String about) {

    this.username = username;
    this.password = password;
    this.about = about;
  }

  public String username() {
    return username;
  }

  public String password() {
    return password;
  }

  public String about() {
    return about;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    RegistrationData that = (RegistrationData) o;

    return new EqualsBuilder()
        .append(username, that.username)
        .append(password, that.password)
        .append(about, that.about)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(username)
        .append(password)
        .append(about)
        .toHashCode();
  }
}
