package com.luxoft.cjp.april16.bankapp.model;

/**
 * Created by KMajewski on 2016-04-12.
 */
public abstract class AbstractAccount implements Account {

    private float balance;

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
