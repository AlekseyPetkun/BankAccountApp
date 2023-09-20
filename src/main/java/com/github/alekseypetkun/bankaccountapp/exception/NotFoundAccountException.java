package com.github.alekseypetkun.bankaccountapp.exception;

public class NotFoundAccountException extends NotFoundException{

    private final Long id;

    public NotFoundAccountException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Аккаунт с id: " + id + " не найден!");
    }
}
