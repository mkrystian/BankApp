package com.luxoft.cjp.april16.bankapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-17.
 */
public abstract class AbstractDao {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected final EntityManager entityManager;


    public AbstractDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public final void close() {
        entityManager.close();
    }
}
