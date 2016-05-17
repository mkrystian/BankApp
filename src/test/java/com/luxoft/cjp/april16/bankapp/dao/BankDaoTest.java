package com.luxoft.cjp.april16.bankapp.dao;

import com.luxoft.cjp.april16.bankapp.BankApplication;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-17.
 */
public class BankDaoTest {

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    Bank bank;
    BankService bankService;
    BankDao bankDao;

    @Before
    public void initialize() {
        bank = new Bank("DaoTest Bank");
        bankService = new BankServiceImpl();
        entityManagerFactory = Persistence.createEntityManagerFactory("BankAppORM");
        entityManager = entityManagerFactory.createEntityManager();
        BankApplication.initialize(bank, bankService);
        bankDao = new BankDao(entityManager);
    }

    @Test
    public void saveBankTest() throws Exception {

        /*Bank persistenceBank =*/
        bankDao.saveBank(bank);
        Bank persistentBank = bankDao.getBankById(1);

        persistentBank.printReport();

        System.out.println(persistentBank.getClientsMap().size());
    }

    @Test
    public void getBankByIdTest() {
        Bank persistentBank = bankDao.getBankById(1);

        persistentBank.printReport();
    }


    @After
    public void close() {
        bankDao.close();
        //entityManager.close();
        //entityManagerFactory.close();
    }


}