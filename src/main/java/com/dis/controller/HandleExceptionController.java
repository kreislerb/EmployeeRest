package com.dis.controller;

import com.dis.controller.response.Response;
import com.dis.exceptions.DisRuntimeException;
import com.dis.exceptions.EmployeeException;
import com.dis.exceptions.ExceptionType;
import com.dis.util.messages.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@Slf4j
public class HandleExceptionController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        log.error(ExceptionType.DIS_EXCEPTION.getType(), ex);
        Response<String> generalResponse = new Response<String>(ResponseStatus.KO.getStatus(), null, ExceptionType.DIS_EXCEPTION.getType(), Arrays.asList(ex.getMessage()));
        return handleExceptionInternal(ex, generalResponse, headers(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {ServerErrorException.class})
    protected ResponseEntity<Object> handleConflict(DisRuntimeException ex, WebRequest request) {
        Response<String> generalResponse = new Response<String>(ResponseStatus.KO.getStatus(), null, ExceptionType.DIS_EXCEPTION.getType(), Arrays.asList(ex.getMessage()));
        return handleExceptionInternal(ex, generalResponse, headers(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    @ExceptionHandler(value = {EmployeeException.class})
    protected ResponseEntity<Object> handleConflict(EmployeeException ex, WebRequest request) {
        Response<String> generalResponse = new Response<String>(ResponseStatus.KO.getStatus(), null, ExceptionType.EMPLOYEE_EXCPETION.getType(), Arrays.asList(ex.getDescription()));
        return handleExceptionInternal(ex, generalResponse, headers(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase().concat(". ").concat(ex.getMessage()));
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        Response<String> generalResponse = new Response<String>(ResponseStatus.KO.getStatus(), null, ex.getLocalizedMessage(),errors);

        return handleExceptionInternal(ex, generalResponse, headers(), status, request);
    }


    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}