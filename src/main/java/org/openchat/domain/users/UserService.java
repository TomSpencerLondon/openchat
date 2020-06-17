package org.openchat.domain.users;

public class UserService {


  private final IdGenerator idGenerator;
  private final UserRepository userRepository;

  public UserService(IdGenerator idGenerator, UserRepository userRepository) {
    this.idGenerator = idGenerator;
    this.userRepository = userRepository;
  }

  public User createUser(RegistrationData registrationData) throws UsernameAlreadyInUseException {
      String userId = idGenerator.next();
      User user = createUserFrom(userId, registrationData);
      userRepository.add(user);
      return user;
  }

  private User createUserFrom(String userId, RegistrationData registrationData) {
    return new User(userId, registrationData.username(),
        registrationData.password(),
        registrationData.about());
  }
}
