package com.luxoft.cjp.april16.bankapp.model.model;

import com.luxoft.cjp.april16.bankapp.model.model.exceptions.NotEnoughFoundsException;
import com.luxoft.cjp.april16.bankapp.service.Report;

import java.math.BigDecimal;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public interface Account extends Report {
    int getId();

    float getBalance();

    void deposit(float x);

    void withdraw(float x) throws NotEnoughFoundsException;

    BigDecimal decimalValue();

    String toString();
}