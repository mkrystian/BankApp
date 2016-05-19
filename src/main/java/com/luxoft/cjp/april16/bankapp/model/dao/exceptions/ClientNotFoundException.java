package com.luxoft.cjp.april16.bankapp.model.dao.exceptions;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-19.
 */
public class ClientNotFoundException extends AbstractNotFoundException {
    public ClientNotFoundException(String name) {
        super(name);
    }
}
