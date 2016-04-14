package com.luxoft.cjp.april16.bankapp.service;

import com.luxoft.cjp.april16.bankapp.model.Account;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;



/**
 * Created by KMajewski on 2016-04-12.
 */
public interface BankService {
    void addClient(Bank bank, Client client);
    void removeClient(Bank bank, Client client);
    void addAccount(Client client, Account account);
    void setActiveAccount(Client client, Account account);
}
