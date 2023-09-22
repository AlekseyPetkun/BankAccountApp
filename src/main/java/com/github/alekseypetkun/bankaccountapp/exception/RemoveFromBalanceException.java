package com.github.alekseypetkun.bankaccountapp.exception;

public class RemoveFromBalanceException extends NotFoundException{

    public RemoveFromBalanceException(Integer balance) {
        super("На счете: " + balance + ", что меньше запрашиваемой суммы!");
    }
}
