package by.example.bookstore_api.config;

import by.example.bookstore_api.exception.AuthorExists;
import by.example.bookstore_api.exception.BookstoreException;
import by.example.bookstore_api.model.dto.exception.ExceptionResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionGlobalHandler {
  private static final String ERROR_LOGGING_USING_URI_URL =
      "An error occurred: message='{}', URL='{}'";
  private static final String ERROR_LOG_FORMAT = "Error: [message='{}'], [URI='{}']";

  @ExceptionHandler(BookstoreException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponseDto handleAuthorExistsException(HttpServletRequest request,
      AuthorExists exception) {
    log.warn(ERROR_LOG_FORMAT, exception.getMessage(), request.getRequestURL());
    return buildResponse(exception, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponseDto handleValidationException(HttpServletRequest request,
      MethodArgumentNotValidException exception) {

    log.error("Validation error: {}", exception.getMessage());
    return buildResponse(exception, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ExceptionResponseDto handleException(HttpServletRequest request, Exception exception) {
    log.error(ERROR_LOG_FORMAT, exception.getMessage(), request.getRequestURL(), exception);
    //todo remove verbose messaging here
    return buildResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler(value = EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ExceptionResponseDto entityNotFoundException(HttpServletRequest request,
      Exception exception) {
    log.error(
        ERROR_LOGGING_USING_URI_URL, exception.getMessage(), request.getRequestURL().toString());
    return buildResponse(exception, HttpStatus.NOT_FOUND, request);
  }

  private ExceptionResponseDto buildResponse(
      Exception ex, HttpStatus status, HttpServletRequest request) {

    return ExceptionResponseDto.builder()
        .status(status.value())
        .error(status.getReasonPhrase())
        .message(ex.getMessage())
        .path(request.getRequestURI())
        .build();
  }
}
