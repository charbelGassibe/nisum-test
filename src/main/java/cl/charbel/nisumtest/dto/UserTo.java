package cl.charbel.nisumtest.dto;

import static cl.charbel.nisumtest.util.Constants.*;

import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTo {

  private String name;

  @Pattern(regexp = USER_EMAIL_REGEX, message = USER_EMAIL_MESSAGE)
  private String email;

  @Pattern(regexp = USER_PASSWORD_REGEX, message = USER_PASSWORD_MESSAGE)
  private String password;

  private List<PhoneTo> phones;

  private String created;
  private String modified;
  private String lastLogin;
  private boolean isActive;
  private String token;
}
