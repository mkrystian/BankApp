package com.luxoft.cjp.april16.bankapp.dao;

import com.luxoft.cjp.april16.bankapp.model.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-17.
 */
public class BankDao extends AbstractDao {
    public BankDao(EntityManager entityManager) {
        super(entityManager);
    }

    public final Bank saveBank(final Bank bank) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final Bank persistedBank = entityManager.merge(bank);
        transaction.commit();
        return persistedBank;
    }

    public final Bank getBankById(int id) {
        return entityManager.find(Bank.class, id);
    }
}
