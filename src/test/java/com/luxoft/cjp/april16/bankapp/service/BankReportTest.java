package com.luxoft.cjp.april16.bankapp.service;

import com.luxoft.cjp.april16.bankapp.BankApplication;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-19.
 */
public class BankReportTest {

    Bank bank = new Bank("Test Bank");
    BankService bankService = new BankServiceImpl();
    BankReport bankReport = new BankReport();

    @Before
    public void setUp() throws Exception {
        BankApplication.initialize(bank, bankService);
    }

    @Test
    public void testGetNumberOfClients() throws Exception {
        Assert.assertEquals(bankReport.getNumberOfClients(bank), 5);
    }

    @Test
    public void testGetAccountsNumber() throws Exception {
        Assert.assertEquals(bankReport.getAccountsNumber(bank), 10);
    }

    @Test
    public void testGetBankCreditSum() throws Exception {
        Assert.assertEquals(bankReport.getBankCreditSum(bank), 0, 0);
    }

    @Test
    public void testGetClientsByCity() throws Exception {
        String expectedOutput = "LondonLondonNew JerseyNew YorkWashington";
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, List<Client>> entryClient : bankReport.getClientsByCity(bank).entrySet()) {
            for (Client client : entryClient.getValue()) {
                output.append(client.getCity());
            }
        }

        Assert.assertEquals(expectedOutput, output.toString());
    }
}