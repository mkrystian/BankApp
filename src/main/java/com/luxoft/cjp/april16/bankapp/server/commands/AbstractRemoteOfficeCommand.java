package com.luxoft.cjp.april16.bankapp.server.commands;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.RORequest;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.exceptions.ClientNotFoundByPeselException;


public abstract class AbstractRemoteOfficeCommand implements ROCommand {
    Bank bank;
    BankService bankService;
    String pesel;

    AbstractRemoteOfficeCommand(Bank bank, BankService bankService) {
        this.bank = bank;
        this.bankService = bankService;
    }

    Client getClient(RORequest request) throws ClientNotFoundByPeselException {
        pesel = request.getData()[0];
        Client client;
        client = bankService.getClientByPesel(bank, pesel);

        return client;
    }
}