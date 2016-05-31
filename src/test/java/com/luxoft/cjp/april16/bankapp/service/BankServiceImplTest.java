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
@SuppressWarnings("unused")
public class BankServiceImplTest {

    private static final Bank bank = new Bank("Test Bank");
    private static final BankService bankService = new BankServiceImpl();
    private static Client client;

    @BeforeClass
    @SuppressWarnings("unused")
    public static void setUp() {
        BankApplication.initialize(bank, bankService);
        Iterator<Client> iterator = bankService.getClients(bank).iterator();

        client = iterator.next();

    }

    @Test
    public void saveClient() {
        bankService.saveClient(client);
    }

    @Test
    public void loadClient() {
        Client loadedClient = bankService.loadClient();
        assertThat( "Incorrect data after deserialization" ,client.toString() , is(loadedClient.toString()));
    }

}