package com.luxoft.cjp.april16.bankapp.service;

import com.luxoft.cjp.april16.bankapp.model.model.Account;
import com.luxoft.cjp.april16.bankapp.model.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.model.Client;

import java.util.List;


/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public interface BankService {
    void addClient(Bank bank, Client client);

    void removeClient(Bank bank, Client client);

    void addAccount(Client client, Account account);

    void setActiveAccount(Client client, Account account);

    List<Client> getClients(Bank bank);
}
