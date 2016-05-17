package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;
import com.luxoft.cjp.april16.bankapp.model.exceptions.OverDraftLimitExceededException;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import java.util.Map;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CheckingAccount extends AbstractAccount {

    @Transient
    private float overdraft = 0;

    public CheckingAccount(float balance, float overdraft) {
        super(balance);
        if (overdraft < 0) throw new IllegalArgumentException("Overdraft could not be negative");
        this.overdraft = overdraft;
    }

    public CheckingAccount() {
    }

    public static Account checkingAccountFactoryMethodForFeed(Map<String, String> feed) {
        String balance = feed.get("balance");
        String overdraft = feed.get("overdraft");
        return new CheckingAccount(Float.valueOf(balance), Float.valueOf(overdraft));
    }

    public float getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(float overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    public synchronized void withdraw(float x) throws NotEnoughFoundsException {
        if (super.getBalance() + overdraft >= x) {
            super.setBalance(super.getBalance() - x);
        } else {
            throw new OverDraftLimitExceededException(this, x, super.getBalance(), overdraft);
        }
    }

    @Override
    public void printReport() {
        System.out.println("Account type: Checking account");
        super.printReport();
        System.out.println("Overdraft: " + overdraft);
    }

    @Override
    public String toString() {
        return super.toString() + "|Checking account" + "|" + overdraft;
    }
}
