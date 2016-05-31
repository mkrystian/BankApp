package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.Gender;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.BankNotFoundException;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;
import com.luxoft.cjp.april16.bankapp.model.exceptions.ClientExistsException;
import com.luxoft.cjp.april16.bankapp.service.TestService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-18.
 */
@SuppressWarnings("unused")
public class BankDAOImplTest {
    private Client mockClient;
    private Bank bank;
    private BankDAO bankDAO;

    @Before
    public void setUp() {
        bank = new Bank("DAO Test Bank");
        bank.setId(-1);
        bankDAO = new BankDAOImpl();
        mockClient = new Client("Jena Jenkins", Gender.MALE, "34292302923", 0, "dsd@wsa.pl", "Budapest");
    }

    @Test
    public void getBankByName() throws Exception {
        final Bank bankByName = bankDAO.getBankByName("Database Test Bank");
        assertThat(bankByName.getId(), is(1));
    }

    @Test
    public void saveComparisonRemove() throws Exception {
        bankDAO.save(bank);
        bankDAO.remove(bank);
    }

    @Test
    public void saveAndComparison() throws DAOException, BankNotFoundException, ClientExistsException {
        bankDAO.save(bank);
        Bank loadedBank = bankDAO.getBankByName(bank.getName());
        assertThat(TestService.isEquals(bank, loadedBank), is(true));
        loadedBank.addClient(mockClient);
        assertThat(TestService.isEquals(bank, loadedBank), is(false));
        bankDAO.remove(bank);

    }


}