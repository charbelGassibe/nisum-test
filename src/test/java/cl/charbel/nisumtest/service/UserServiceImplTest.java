package cl.charbel.nisumtest.service;

import static cl.charbel.nisumtest.TestingUtils.getFileContent;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import cl.charbel.nisumtest.dto.UserTo;
import cl.charbel.nisumtest.exception.DuplicateUserException;
import cl.charbel.nisumtest.persistence.UserRepository;
import cl.charbel.nisumtest.persistence.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock private UserRepository userRepository;

  @InjectMocks private UserServiceImpl userService;

  @Test
  void shouldRegisterUser() throws IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    UserTo userTo =
        objectMapper.readValue(getFileContent("request/UserDtoValid.json"), UserTo.class);

    User expectedUser =
        objectMapper.readValue(getFileContent("request/UserDomainValid.json"), User.class);

    when(userRepository.existsByEmail(any())).thenReturn(false);
    when(userRepository.save(any())).thenReturn(expectedUser);

    UserTo result = userService.register(userTo);
    assertEquals(userTo.getEmail(), result.getEmail());
  }

  @Test
  void shouldThrowDuplicateUserException() {
    UserTo userTo = new UserTo();

    when(userRepository.existsByEmail(any())).thenReturn(true);

    assertThrows(DuplicateUserException.class, () -> userService.register(userTo));
  }
}
