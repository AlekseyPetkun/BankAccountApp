package com.github.alekseypetkun.bankaccountapp.exception;

public class AuthenticationException extends RuntimeException{

    private final String password;

    public AuthenticationException(String password) {
        this.password = password;
    }

    @Override
    public String getMessage() {
        return String.format("Введенный пин-код: " + password + " не верный!");
    }
}
