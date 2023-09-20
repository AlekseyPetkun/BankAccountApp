package com.github.alekseypetkun.bankaccountapp.exception;

public class RemoveFromBalanceException extends NotFoundException{

    private final Integer balance;

    public RemoveFromBalanceException(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String getMessage() {
        return String.format("На счете: " + balance + ", что меньше запрашиваемой суммы!");
    }
}
