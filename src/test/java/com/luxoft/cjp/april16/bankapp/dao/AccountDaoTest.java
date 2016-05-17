package com.luxoft.cjp.april16.bankapp.dao;

import com.luxoft.cjp.april16.bankapp.model.Account;
import com.luxoft.cjp.april16.bankapp.model.CheckingAccount;
import com.luxoft.cjp.april16.bankapp.model.SavingAccount;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-17.
 */
public class AccountDaoTest {
    @Test
    public void saveAccount() throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BankAppORM");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        Account savingAccount = new SavingAccount(2000);
        Account checkingAccount = new CheckingAccount(2000, 1000);
        AccountDao accountDao = new AccountDao(entityManager);
        //AccountDao savingAccountDao = new AccountDao(entityManager);

        Account persistenceAccount = accountDao.saveAccount(savingAccount);
        persistenceAccount.withdraw(1999.99f);
        accountDao.saveAccount(checkingAccount);
        accountDao.close();
    }

}