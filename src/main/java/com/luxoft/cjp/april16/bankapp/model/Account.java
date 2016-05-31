package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;
import com.luxoft.cjp.april16.bankapp.service.Report;

import java.io.Serializable;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public interface Account extends Report, Comparable, Serializable {
    int getId();

    void setId(int id);

    float getBalance();

    void deposit(float x);

    void withdraw(float x) throws NotEnoughFoundsException;

    void transferTo(Account account, float amount) throws NotEnoughFoundsException;

    String toString();

    AccountType getType();
}
