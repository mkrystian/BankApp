package com.luxoft.cjp.april16.bankapp.server;

import com.luxoft.cjp.april16.bankapp.BankApplication;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class ServerTest {

    private Server server;

    @Before
    public void setUp() {
        Bank bank = new Bank("Test Bank");
        BankService bankService = new BankServiceImpl();
        server = new Server(4231, bank, bankService);
        BankApplication.initialize(bank, bankService);
    }

    @Test
    public void run() throws Exception {

        Thread thread = new Thread(server);
        thread.start();
        (new Scanner(System.in)).next();
        thread.interrupt();
    }

}