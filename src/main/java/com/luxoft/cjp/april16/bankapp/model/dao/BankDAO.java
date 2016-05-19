package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.BankInfo;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.BankNotFoundException;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

public interface BankDAO extends BaseDAO {
    /**
     * Finds Bank by its name.
     * Do not load the list of the clients.
     *
     * @ Param name
     * @ Return
     */
    Bank getBankByName(String name) throws DAOException, BankNotFoundException;

    void save(Bank bank) throws DAOException;

    void remove(Bank bank) throws DAOException;

    BankInfo getBankInfo(Bank bank) throws DAOException;

}