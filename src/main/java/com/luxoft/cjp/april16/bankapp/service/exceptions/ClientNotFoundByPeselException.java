package com.luxoft.cjp.april16.bankapp.service.exceptions;

import java.io.IOException;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class ClientNotFoundByPeselException extends IOException {

    private String pesel;

    public ClientNotFoundByPeselException(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String getMessage() {
        return ("Client with pesel: " + pesel + "doesn't exist");
    }
}
