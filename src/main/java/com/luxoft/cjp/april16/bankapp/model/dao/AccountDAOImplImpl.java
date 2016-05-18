package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.Account;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

import java.util.List;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-18.
 */
public class AccountDAOImplImpl extends BaseDAOImpl implements AccountDAO {
    @Override
    public void save(Account account) throws DAOException {
        throw new UnsupportedOperationException("AccountDAOImplImpl not implemented");
    }

    @Override
    public void add(Account account) throws DAOException {
        throw new UnsupportedOperationException("AccountDAOImplImpl not implemented");
    }

    @Override
    public void removeByClientId(int idClient) throws DAOException {
        throw new UnsupportedOperationException("AccountDAOImplImpl not implemented");
    }

    @Override
    public List<Account> getClientAccounts(int idClient) throws DAOException {
        throw new UnsupportedOperationException("AccountDAOImplImpl not implemented");
    }
}
