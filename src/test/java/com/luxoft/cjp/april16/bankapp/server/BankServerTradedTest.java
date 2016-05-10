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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-09.
 */
public class BankServerTradedTest {

    @Test
    public void multithreadingTest() throws InterruptedException {
        int port = 2004;
        final int numberOfThreads = 1000;
        Bank bank = new Bank("Mock bank");
        BankService bankService = new BankServiceImpl();
        BankServerTraded bankServer = new BankServerTraded(bank, bankService, port);
        Client client = new Client("Mock Client", Gender.MALE, "78021298512", 2000, "mock@mail.com", "Mock");
        Account savingAccount = new CheckingAccount(numberOfThreads, 0);
        client.addAccount(savingAccount);
        client.setActiveAccount(savingAccount);
        try {
            bank.addClient(client);
        } catch (ClientExistsException e) {
            e.printStackTrace();
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Thread bankServerThread = new Thread(bankServer);
        bankServerThread.start();

        List<Future<Long>> threadList = new ArrayList<>(1000);
        for (int i = 0; i < numberOfThreads; i++) {
            Future<Long> future = executor.submit(new BankClientMock("localhost", port, new IdentityCard(IdentityType.ATM, "Client Mock"), client));
            threadList.add(future);
        }

        double sumTime = 0;
        for (Future future : threadList) {
            try {
                sumTime += (Long) future.get();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(savingAccount.getBalance());
        System.out.println(sumTime / numberOfThreads);


    }
}