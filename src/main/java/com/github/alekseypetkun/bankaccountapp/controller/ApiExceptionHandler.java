package com.github.alekseypetkun.bankaccountapp.controller;

import com.github.alekseypetkun.bankaccountapp.exception.AuthenticationException;
import com.github.alekseypetkun.bankaccountapp.exception.ExistingAccountException;
import com.github.alekseypetkun.bankaccountapp.exception.NotFoundException;
import com.github.alekseypetkun.bankaccountapp.exception.RemoveFromBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Функция Spring для обработки исключений
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handlerNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handlerExistingAccountException(ExistingAccountException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<String> handlerAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<String> handlerRemoveFromBalanceException(RemoveFromBalanceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
