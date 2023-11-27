package cl.charbel.nisumtest.api;

import static org.junit.jupiter.api.Assertions.*;

import cl.charbel.nisumtest.dto.UserTo;
import cl.charbel.nisumtest.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock private UserService userService;

  @InjectMocks private UserController userController;

  @Test
  void shouldRegisterUserWhenIsValidDto() {
    UserTo userTo = new UserTo();

    userController.registerUser(userTo);
  }
}
