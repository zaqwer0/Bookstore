package by.example.bookstore_api.exceptionHandler;

import by.example.bookstore_api.model.dto.exception.ExceptionResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionGlobalHandler {
    private static final String ERROR_LOGGING_USING_URI_URL = "An error occurred: message='{}', URL='{}'";

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseDto entityNotFoundException(HttpServletRequest request, Exception exception) {
        log.error(ERROR_LOGGING_USING_URI_URL, exception.getMessage(), request.getRequestURL().toString());
        return new ExceptionResponseDto(exception.getMessage());
    }
}
