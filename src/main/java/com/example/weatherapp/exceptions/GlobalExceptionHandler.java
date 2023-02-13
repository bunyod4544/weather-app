package com.example.weatherapp.exceptions;

import com.example.weatherapp.response.ResponseEntity;
import com.example.weatherapp.response.ResponseError;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;

/**
 * @author Doston Bokhodirov on 05 November 2022 at 6:22 PM
 */

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(final NotFoundException ex, WebRequest webRequest) {
        return getResponseEntity(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUserNameNotFoundException(final UsernameNotFoundException ex, WebRequest webRequest) {
        return getResponseEntity(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(final BadRequestException ex, WebRequest webRequest) {
        return getResponseEntity(ex, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(final ConstraintViolationException ex, WebRequest webRequest) {
        return getResponseEntity(ex, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommonFileException.class)
    public ResponseEntity<?> handleCommonFileException(final CommonFileException ex, WebRequest webRequest) {
        return getResponseEntity(ex, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomFirebaseException.class)
    public ResponseEntity<?> handleCustomFirebaseException(final CustomFirebaseException ex, WebRequest webRequest) {
        return getResponseEntity(ex, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(final Exception ex, WebRequest webRequest) {
        return getResponseEntity(ex, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private <E extends Exception> ResponseEntity<Object> getResponseEntity(E ex, WebRequest webRequest, HttpStatus status) {
        return ResponseEntity.error(ResponseError.response(status.getReasonPhrase(), ex.getMessage(), webRequest), status);
    }

    @Override
    protected org.springframework.http.ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .toList();
        return new org.springframework.http.ResponseEntity<>(
                ResponseEntity.ok(
                        ResponseError.response(
                                status.getReasonPhrase(),
                                String.join(", ", errorMessages)
                        )
                ),
                status
        );
    }
}
