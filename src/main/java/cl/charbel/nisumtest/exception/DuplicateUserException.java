package cl.charbel.nisumtest.exception;

public class DuplicateUserException extends RuntimeException {

  public DuplicateUserException(String message) {
    super(message);
  }
}
