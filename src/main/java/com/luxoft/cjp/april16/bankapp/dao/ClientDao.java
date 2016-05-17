package com.luxoft.cjp.april16.bankapp.dao;

import com.luxoft.cjp.april16.bankapp.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-17.
 */
public class ClientDao extends AbstractDao {
    public ClientDao(EntityManager entityManager) {
        super(entityManager);
    }

    public final Client saveClient(final Client client) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final Client persistedClient = entityManager.merge(client);
        transaction.commit();
        return persistedClient;
    }
}
