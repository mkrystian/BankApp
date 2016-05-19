package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.Account;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

import java.util.Set;

interface AccountDAO extends BaseDAO {
    void save(Account account, Bank bank, Client client) throws DAOException;

    void remove(Account account) throws DAOException;

    Set<Account> getClientAccounts(Client client) throws DAOException;
}