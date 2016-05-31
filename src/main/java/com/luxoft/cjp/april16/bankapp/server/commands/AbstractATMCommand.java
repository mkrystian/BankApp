package com.luxoft.cjp.april16.bankapp.server.commands;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequest;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.exceptions.ClientNotFoundByPeselException;


public abstract class AbstractATMCommand implements ATMCommand {
    private final Bank bank;
    private final BankService bankService;

    AbstractATMCommand(Bank bank, BankService bankService) {
        this.bank = bank;
        this.bankService = bankService;
    }

    Client getClient(ATMRequest request) throws ClientNotFoundByPeselException {
        String pesel = request.getData()[0];
        Client client;
        client = bankService.getClientByPesel(bank, pesel);
        return client;
    }
}