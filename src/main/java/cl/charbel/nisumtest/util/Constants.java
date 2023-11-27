package cl.charbel.nisumtest.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

  public static final String USER_EMAIL_REGEX =
      "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

  public static final String USER_EMAIL_MESSAGE = "El formato del correo electrónico no es válido";
  public static final String USER_PASSWORD_REGEX =
      "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

  public static final String USER_PASSWORD_MESSAGE =
      """
            La clave debe coincidir con los siguientes criterios
            Al menos 8 caracteres de longitud.
            Al menos una letra mayúscula (A-Z).
            Al menos una letra minúscula (a-z).
            Al menos un dígito (0-9).
            Al menos un carácter especial como @, #, $, %, ^, &, +, = o !.
            No permite espacios en blanco.
            """;
}
