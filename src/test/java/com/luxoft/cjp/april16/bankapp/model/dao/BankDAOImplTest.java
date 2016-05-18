package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-18.
 */
public class BankDAOImplTest {
    Bank bank;
    BankService bankservice;
    BankDAO bankDAO;

    @Before
    public void setUp() throws Exception {
        bank = new Bank("Test Bank");
        bank.setId(-1);
        bankservice = new BankServiceImpl();
        bankDAO = new BankDAOImpl();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getBankByName() throws Exception {
        final Bank bankByName = bankDAO.getBankByName("Database Test Bank");
        assertThat(bankByName.getId(), is(1));
    }

    @Test
    public void saveAndRemove() throws Exception {
        bankDAO.save(bank);
        //System.out.println(bank.getId());
        bankDAO.remove(bank);
    }


}