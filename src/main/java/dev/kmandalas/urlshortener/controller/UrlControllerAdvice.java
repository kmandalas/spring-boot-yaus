package dev.kmandalas.urlshortener.controller;

import dev.kmandalas.urlshortener.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@Slf4j
public class UrlControllerAdvice {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDto> handleApi(final ResponseStatusException exception) {
        final ErrorDto errorResponse = new ErrorDto(exception.getStatus().value(),
            exception.getClass().getSimpleName(), exception.getMessage());

        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDto> handleThrowable(final Throwable exception) {
        log.error("Whoops... -> ", exception);
        final ErrorDto errorResponse = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            exception.getClass().getSimpleName(), exception.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
