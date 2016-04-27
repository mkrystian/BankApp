package com.luxoft.cjp.april16.bankapp.model.exceptions;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-27.
 */
public class IncorrectGenderException extends RuntimeException {

    String message;

    public IncorrectGenderException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
