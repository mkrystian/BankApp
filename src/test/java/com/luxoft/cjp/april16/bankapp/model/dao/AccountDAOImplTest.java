package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-18.
 */
public class AccountDAOImplTest {

    private AccountDAO accountDAO;
    private Client mockClient;
    private Bank mockBank;
    private Account checkingAccount;
    private Account savingAccount;

    @Before
    public void setUp() throws Exception {
        accountDAO = new AccountDAOImpl();
        mockClient = Mockito.mock(Client.class);
        mockBank = Mockito.mock(Bank.class);
        Mockito.when(mockClient.getId()).thenReturn(1);
        Mockito.when(mockBank.getId()).thenReturn(1);

        checkingAccount = new CheckingAccount(999, 1999);
        savingAccount = new SavingAccount(499);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void saveAndRemove() throws Exception {
        accountDAO.save(checkingAccount, mockBank, mockClient);
        accountDAO.save(savingAccount, mockBank, mockClient);

        accountDAO.remove(checkingAccount);
        accountDAO.remove(savingAccount);
    }


    @Test
    public void getClientAccounts() throws Exception {
        final Set<Account> clientAccounts = accountDAO.getClientAccounts(mockClient);
        assertThat(clientAccounts.size(), is(3));
    }

}