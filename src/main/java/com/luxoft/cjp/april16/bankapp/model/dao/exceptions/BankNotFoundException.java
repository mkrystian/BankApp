package com.luxoft.cjp.april16.bankapp.model.dao.exceptions;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-18.
 */
public class BankNotFoundException extends Exception {

    String name;

    public BankNotFoundException(String name) {
        this.name = name;
    }
}
