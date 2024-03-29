package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;

import java.util.Map;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public class SavingAccount extends AbstractAccount {

    public SavingAccount(float balance) {
        super(balance, AccountType.SAVING_ACCOUNT);
        if (balance < 0) throw new IllegalArgumentException("Balance could not be negative in saving account");
    }

    public SavingAccount(float balance, int id) {
        super(balance, AccountType.SAVING_ACCOUNT, id);
        if (balance < 0) throw new IllegalArgumentException("Balance could not be negative in saving account");
    }

    static Account savingAccountFactoryMethodForFeed(Map<String, String> feed) {
        String balance = feed.get("balance");
        return new SavingAccount(Float.valueOf(balance));
    }

    @Override
    public synchronized void withdraw(float x) throws NotEnoughFoundsException {
        if (super.getBalance() >= x) {
            super.setBalance(super.getBalance() - x);
        } else {
            throw new NotEnoughFoundsException(this, x, super.getBalance());
        }
    }

    @Override
    public void printReport() {
        System.out.println("Account type: Saving account");
        super.printReport();
    }

    @Override
    public String toString() {
        return super.toString() + "|Saving account";
    }
}
