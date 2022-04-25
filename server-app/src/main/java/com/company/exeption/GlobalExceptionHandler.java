package com.company.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.zalando.problem.*;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;
import static org.zalando.problem.Status.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Problem> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Problem problem = Problem.builder()
            .withTitle(BAD_REQUEST.getReasonPhrase())
            .withStatus(BAD_REQUEST)
            .withDetail(format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                ex.getName(), ex.getValue(), ex.getRequiredType())).build();
        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Problem> handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(Problem.builder()
            .withTitle(NOT_FOUND.getReasonPhrase())
            .withStatus(NOT_FOUND)
            .withDetail("Unable to find entity with " + ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<Problem> handleEntityAlreadyExists(EntityAlreadyExistsException ex) {
        return new ResponseEntity<>(Problem.builder()
            .withTitle(CONFLICT.getReasonPhrase())
            .withStatus(CONFLICT)
            .withDetail("Entity with " + ex.getMessage() + " is already exists").build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Problem> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(Problem.builder()
            .withTitle(UNAUTHORIZED.getReasonPhrase())
            .withStatus(UNAUTHORIZED)
            .withDetail(ex.getMessage()).build(), HttpStatus.UNAUTHORIZED);
    }
}
