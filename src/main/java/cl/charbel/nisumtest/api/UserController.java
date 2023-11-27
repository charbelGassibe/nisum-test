package cl.charbel.nisumtest.api;

import cl.charbel.nisumtest.dto.UserTo;
import cl.charbel.nisumtest.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/user/register")
  public ResponseEntity registerUser(@RequestBody @Valid UserTo userTo) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userTo));
  }
}
