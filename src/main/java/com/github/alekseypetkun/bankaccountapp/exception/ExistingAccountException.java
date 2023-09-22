package com.github.alekseypetkun.bankaccountapp.exception;

public class ExistingAccountException extends RuntimeException{

    public ExistingAccountException(String username) {
        super("Аккаунт с именем: " + username + " уже существует!");
    }
}

