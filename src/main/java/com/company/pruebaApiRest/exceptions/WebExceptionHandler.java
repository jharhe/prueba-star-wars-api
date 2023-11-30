package com.company.pruebaApiRest.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.company.pruebaApiRest.response.FilmResponseRest;

@Slf4j
@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void handleGeneralException(Exception exception) {
        log.error("internal_error: " + exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void handleMethodArgumentNotValidException(MethodArgumentTypeMismatchException exception) {    	
        log.error("error en la solicitud parameter_error.%s: %s".formatted(exception.getName(), exception.getMostSpecificCause().getMessage()));
    }


}
