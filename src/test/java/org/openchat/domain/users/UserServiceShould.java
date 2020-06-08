package org.openchat.domain.users;

import static org.mockito.BDDMockito.given;
import static org.openchat.infrastructure.builders.UserBuilder.aUser;

import java.util.UUID;
import org.junit.Test;

public class UserServiceShould {

  private static final User USER = aUser().build();
  private static final String USER_ID = UUID.randomUUID().toString();

  private IdGenerator idGenerator;

  @Test
  public void create_a_user() {
    // TODO: OpenChat Video 1: 7:23 to end
    given(idGenerator.next()).willReturn(USER_ID);

    User result = userService.createUser(REGISTRATION_DATA);
    verify(userRepository).add(USER);
    assertThat(result).isEqualTo(USER);
  }
}
