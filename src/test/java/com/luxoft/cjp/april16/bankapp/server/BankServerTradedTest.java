package com.luxoft.cjp.april16.bankapp.server;

import com.luxoft.cjp.april16.bankapp.atm.BankClientMock;
import com.luxoft.cjp.april16.bankapp.model.*;
import com.luxoft.cjp.april16.bankapp.model.exceptions.ClientExistsException;
import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityCard;
import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityType;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-09.
 */
public class BankServerTradedTest {

    @Test
    public void multithreadingTest() throws InterruptedException {
        int port = 2004;
        Bank bank = new Bank("Mock bank");
        BankService bankService = new BankServiceImpl();
        BankServerTraded bankServer = new BankServerTraded(bank, bankService, port);
        Client client = new Client("Mock Client", Gender.MALE, "78021298512", 2000, "mock@mail.com", "Mock");
        Account savingAccount = new CheckingAccount(1000, 0);
        client.addAccount(savingAccount);
        client.setActiveAccount(savingAccount);
        try {
            bank.addClient(client);
        } catch (ClientExistsException e) {
            e.printStackTrace();
        }

        Thread bankServerThread = new Thread(bankServer);
        bankServerThread.start();
        Thread.sleep(1000);
        List<Thread> threadList = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            threadList.add(new Thread(new BankClientMock("localhost", port, new IdentityCard(IdentityType.ATM, "Client Mock"), client)));
        }
        threadList.forEach(Thread::start);

        for (Thread thread : threadList) {
            thread.join();
        }

        System.out.println(savingAccount.getBalance());


        //bankServer.setRunning(false);

    }
}