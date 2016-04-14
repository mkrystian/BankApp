package com.luxoft.cjp.april16.bankapp.model.exceptions;

import com.luxoft.cjp.april16.bankapp.model.Account;

/**
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

    public String getInfo(){
        StringBuilder output = new StringBuilder();
        output.append("Amount: ").append(amount).append(" |  Balance: ").append(balance);
        return output.toString();
    }

    public Account getAccount() {
        return account;
    }
}
