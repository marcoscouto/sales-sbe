package io.github.marcoscouto.rest.controller;

import io.github.marcoscouto.exception.BusinessRuleException;
import io.github.marcoscouto.exception.OrderNotFoundException;
import io.github.marcoscouto.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessRuleException(BusinessRuleException e){
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleOrderNotFoundException(OrderNotFoundException e) {return new ApiErrors(e.getMessage());}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
         return new ApiErrors(errors);
    }
}

