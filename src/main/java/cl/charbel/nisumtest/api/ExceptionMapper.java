package cl.charbel.nisumtest.api;

import cl.charbel.nisumtest.dto.GenericResponseTo;
import cl.charbel.nisumtest.exception.DuplicateUserException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionMapper {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GenericResponseTo> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    GenericResponseTo responseTo =
        GenericResponseTo.builder()
            .mensaje(errors.values().stream().collect(Collectors.joining("")))
            .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseTo);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity handleRuntime(RuntimeException runtimeException) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(runtimeException.getMessage());
  }

  @ExceptionHandler(DuplicateUserException.class)
  public ResponseEntity handleRuntimeOtraException(DuplicateUserException runtimeException) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(GenericResponseTo.builder().mensaje(runtimeException.getMessage()).build());
  }
}
