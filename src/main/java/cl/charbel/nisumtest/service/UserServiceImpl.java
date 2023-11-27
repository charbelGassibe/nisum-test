package cl.charbel.nisumtest.service;

import cl.charbel.nisumtest.dto.UserTo;
import cl.charbel.nisumtest.exception.DuplicateUserException;
import cl.charbel.nisumtest.persistence.UserRepository;
import cl.charbel.nisumtest.persistence.domain.User;
import cl.charbel.nisumtest.translator.UserTranslator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserTo register(UserTo userTo) {

    validateUser(userTo);

    User user = UserTranslator.toEntity(userTo);

    User persistedUser = userRepository.save(user);

    UserTo persistedUserTo = UserTranslator.toTo(persistedUser);

    return persistedUserTo;
  }

  private void validateUser(UserTo userTo) {

    if (userRepository.existsByEmail(userTo.getEmail())) {
      throw new DuplicateUserException(
          String.format("El correo %s ya esta registrado", userTo.getEmail()));
    }
  }
}
