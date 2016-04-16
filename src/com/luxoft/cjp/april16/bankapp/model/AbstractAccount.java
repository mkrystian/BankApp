package com.luxoft.cjp.april16.bankapp.model;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public abstract class AbstractAccount implements Account {

    private float balance;

    public AbstractAccount(float balance) {
        if (balance < 0) throw new IllegalArgumentException("Balance could not be negative in saving account");
        this.balance = balance;
    }

    @Override
    public float getBalance() {
        return balance;
    }

    void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(float x) {
        setBalance(balance + x );
    }

    @Override
    public void printReport() {
        System.out.println( "Balance: " + getBalance() );
    }
}
