package com.luxoft.cjp.april16.bankapp.model.model.exceptions;

import com.luxoft.cjp.april16.bankapp.model.model.Account;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-13.
 */
public class NotEnoughFoundsException extends BankException {

    private final Account account;
    private final float amount;
    private final float balance;

    public NotEnoughFoundsException(Account account, float amount, float balance) {
        this.account = account;
        this.amount = amount;
        this.balance = balance;
    }

    public float getAmount() {
        return amount;
    }

    public float getBalance() {
        return balance;
    }

    public String getInfo() {
        return "Amount: " + amount + " |  Balance: " + balance;
    }

    public Account getAccount() {
        return account;
    }
}
