package com.luxoft.cjp.april16.bankapp.dao;

import com.luxoft.cjp.april16.bankapp.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-17.
 */
public class AccountDao extends AbstractDao {

    public AccountDao(EntityManager entityManager) {
        super(entityManager);
    }

    public final Account saveAccount(final Account account) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final Account persistedAccount = entityManager.merge(account);
        transaction.commit();
        return persistedAccount;
    }
}
