package com.github.alekseypetkun.bankaccountapp.exception;

public class ExistingAccountException extends RuntimeException{

    private final String username;

    public ExistingAccountException(String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return String.format("Аккаунт с именем: " + username + " уже существует!");
    }
}

