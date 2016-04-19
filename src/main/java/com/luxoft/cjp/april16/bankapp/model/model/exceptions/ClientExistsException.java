package com.luxoft.cjp.april16.bankapp.model.model.exceptions;

import com.luxoft.cjp.april16.bankapp.model.model.Client;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-13.
 */
public class ClientExistsException extends BankException {
    private final Client client;

    public ClientExistsException(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public String getClientName() {
        return client.getName();
    }
}
