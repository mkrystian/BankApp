package com.luxoft.cjp.april16.bankapp.model.dao.exceptions;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-19.
 */
abstract class AbstractNotFoundException extends Exception {

    private String name;

    AbstractNotFoundException() {
    }

    AbstractNotFoundException(String name) {

        this.name = name;
    }

}
