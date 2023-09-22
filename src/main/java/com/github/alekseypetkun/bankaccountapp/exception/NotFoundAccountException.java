package com.github.alekseypetkun.bankaccountapp.exception;

public class NotFoundAccountException extends NotFoundException{

    public NotFoundAccountException(Long id) {
        super("Аккаунт с id: " + id + " не найден!");
    }
}
