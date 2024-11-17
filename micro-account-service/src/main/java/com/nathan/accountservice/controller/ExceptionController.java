package com.nathan.accountservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestControllerAdvice
public class ExceptionController {

    Logger logger = Logger.getLogger(ExceptionController.class.getName());

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> conflicData(Exception ex) {
        logger.info(ex.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("code", "409");
        map.put("message", "Data conflict error. Please check your data and try again.");
        
        return map;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, String> methodNotAllowed(Exception ex) {
        logger.info(ex.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("code", "405");
        map.put("message", "Method not allowed. Please check your HTTP method.");

        return map;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> badRequest(Exception ex) {
        logger.info(ex.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("code", "400");
        map.put("message", "Bad request. Please check your request body.");

        return map;
    }

}
