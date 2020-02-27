package com.tiagods.cartoes.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<?> accountException(AccountException ex, HttpServletResponse response) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, Arrays.asList(ex.getMessage()));
    }
    @ExceptionHandler(OperationTypeException.class)
    public ResponseEntity<?> operationException(OperationTypeException ex, HttpServletResponse response) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, Arrays.asList(ex.getMessage()));
    }
    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<?> transactionException(TransactionException ex, HttpServletResponse response) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, Arrays.asList(ex.getMessage()));
    }
    @ExceptionHandler(LimiteNaoDisponivelException.class)
    public ResponseEntity<?> transactionException(LimiteNaoDisponivelException ex, HttpServletResponse response) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, Arrays.asList(ex.getMessage()));
    }

    @ExceptionHandler(value = InvalidFormatException.class)
    public ResponseEntity<?> processValidationError(InvalidFormatException ex, HttpServletResponse response) {
        return buildResponseEntity(BAD_REQUEST, Arrays.asList(ex.getMessage()));
    }

    private ResponseEntity<?> buildResponseEntity(HttpStatus status, List<String> erros) {
        ResultError resultError = new ResultError(new Date(),status.value(), erros);
        return new ResponseEntity<>(resultError, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ResultError error = new ResultError();
        error.setTimestamp(new Date());
        error.setStatus(status.value());
        error.setErrors(ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList()));
        return new ResponseEntity<>(error, headers, status);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ResultError{
        private Date timestamp;
        private int status;
        private List<String> errors;
    }
}