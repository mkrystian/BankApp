package com.luxoft.cjp.april16.bankapp.service;

import com.luxoft.cjp.april16.bankapp.model.Account;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.service.exceptions.ClientNotFoundByPeselException;

import java.util.List;
import java.util.Set;


/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public interface BankService {
    void addClient(Bank bank, Client client);

    void removeClient(Bank bank, Client client);

    void addAccount(Client client, Account account);

    @SuppressWarnings("unused")
    void setActiveAccount(Client client, Account account);

    Set<Account> getAccounts(Client client);

    Set<Client> getClients(Bank bank);

    List<Client> getClientsByName(Bank bank, String name);

    void saveClient( Client client );

    Client loadClient();

    Client getClientByPesel(Bank bank, String pesel) throws ClientNotFoundByPeselException;
}
