package com.hrproject.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRunTimeException(RuntimeException ex) {

        ex.printStackTrace();

        log.error(ex.toString());

        return new ResponseEntity<>(createError(ErrorType.UNEXPECTED_ERROR, ex, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserManagerException.class)
    public ResponseEntity<ErrorMessage> handleManagerException(UserManagerException exception) {

        ErrorType errorType = exception.getErrorType();

        HttpStatus httpStatus = errorType.getHttpStatus();

        ErrorMessage errorMessage = createError(errorType, exception);

        errorMessage.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        ErrorType errorType = ErrorType.BAD_REQUEST;

        List<String> fields = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));

        ErrorMessage errorMessage = createError(errorType, ex);

        errorMessage.setFields(fields);

        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentConstraintViolationException(DataIntegrityViolationException ex) {

        ErrorType errorType = ErrorType.BAD_REQUEST;

        return new ResponseEntity<>(createError(errorType, ex), errorType.getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(HttpMessageNotReadableException exception) {

        ErrorType errorType = ErrorType.BAD_REQUEST;

        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
            InvalidFormatException exception) {

        ErrorType errorType = ErrorType.BAD_REQUEST;

        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {

        ErrorType errorType = ErrorType.BAD_REQUEST;

        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {

        ErrorType errorType = ErrorType.BAD_REQUEST;

        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception exception) {

        ErrorType errorType = ErrorType.INTERNAL_ERROR_SERVER;

        List<String> fields = new ArrayList<>();

        fields.add(exception.getMessage());

        ErrorMessage errorMessage = createError(errorType, exception);

        errorMessage.setFields(fields);

        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    private ErrorMessage createError(ErrorType errorType, Exception exception) {

        System.out.println("Hata Oluştu: " + exception.getMessage());

        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }

    private ErrorMessage createError(ErrorType errorType, Exception exception, String message) {

        System.out.println("Hata Oluştu: " + exception.getMessage());

        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(message)
                .build();
    }
}