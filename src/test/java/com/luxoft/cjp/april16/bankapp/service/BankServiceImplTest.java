package com.luxoft.cjp.april16.bankapp.service;

import com.luxoft.cjp.april16.bankapp.BankApplication;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Bank Application for CJP
 * Created by PREZES on 2016-04-27.
 */
public class BankServiceImplTest {

    private static Bank bank = new Bank("Test Bank");
    private static BankService bankService = new BankServiceImpl();
    private static Client client;

    @BeforeClass
    public static void setUp() throws Exception {
        BankApplication.initialize(bank, bankService);
        Iterator<Client> iterator = bankService.getClients(bank).iterator();

        client = iterator.next();

    }

    @Test
    public void saveClient() throws Exception {
        bankService.saveClient(client);
    }

    @Test
    public void loadClient() throws Exception {
        Client loadedClient = bankService.loadClient();
        assertThat( "Incorrect data after deserialization" ,client.toString() , is(loadedClient.toString()));
    }

}