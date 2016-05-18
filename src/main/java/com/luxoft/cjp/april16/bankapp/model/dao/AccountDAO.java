package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.Account;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

import java.util.List;

interface AccountDAO extends BaseDAO {
    void save(Account account) throws DAOException;

    void add(Account account) throws DAOException;

    void removeByClientId(int idClient) throws DAOException;

    List<Account> getClientAccounts(int idClient) throws DAOException;
}