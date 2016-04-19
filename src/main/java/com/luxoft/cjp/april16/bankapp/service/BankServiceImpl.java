package com.luxoft.cjp.april16.bankapp.service;

import com.luxoft.cjp.april16.bankapp.model.Account;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.exceptions.ClientExistsException;

import java.util.List;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public class BankServiceImpl implements BankService {

    //  private final Bank bank = new Bank();

    @Override
    public void addClient(Bank bank, Client client) {
        try {
            bank.addClient(client);
        } catch (ClientExistsException e) {
            System.out.println("Client: " + e.getClientName() + " already exists");
        }
    }

    @Override
    public void removeClient(Bank bank, Client client) {
        bank.removeClient(client);
    }

    @Override
    public void addAccount(Client client, Account account) {
        client.addAccount(account);
    }

    @Override
    public void setActiveAccount(Client client, Account account) {
        client.setActiveAccount(account);
    }

    @Override
    public List<Client> getClients(Bank bank) {
        return bank.getClients();
    }


}
