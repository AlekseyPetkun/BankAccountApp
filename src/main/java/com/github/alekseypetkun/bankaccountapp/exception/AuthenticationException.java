package com.github.alekseypetkun.bankaccountapp.exception;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String password) {
        super("Введенный пин-код: " + password + " не верный!");
    }
}
