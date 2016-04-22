package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;

import java.math.BigDecimal;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public abstract class AbstractAccount implements Account {

    private static int autoincrement = 0;
    private int id = ++autoincrement;
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
        setBalance(balance + x);
    }

    @Override
    public void printReport() {
        System.out.println("Balance: " + getBalance());
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAccount)) return false;

        AbstractAccount that = (AbstractAccount) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public BigDecimal decimalValue() {
        return new BigDecimal(Math.round(balance));
    }

    @Override
    public String toString() {
        return id + "|" + balance;
    }

    @Override
    public void transferTo(Account account, float amount) throws NotEnoughFoundsException {
            this.withdraw(amount);
            account.deposit(amount);
    }

}
