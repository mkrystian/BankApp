package com.luxoft.cjp.april16.bankapp.server;

import com.luxoft.cjp.april16.bankapp.BankApplication;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-09.
 */
public class BankServerTraded implements Runnable {

    private static final int POOL_SIZE = 100;
    private Bank bank;
    private BankService bankService;
    private int port;
    private ExecutorService pool;
    private boolean running = true;

    public BankServerTraded(Bank bank, BankService bankService, int port) {
        this.bank = bank;
        this.bankService = bankService;
        this.port = port;
        pool = Executors.newFixedThreadPool(POOL_SIZE);
    }

    public static void main(final String args[]) {
        BankService bankService = new BankServiceImpl();
        Bank bank = new Bank("TestBank");
        BankApplication.initialize(bank, bankService);
        BankServerTraded server = new BankServerTraded(bank, bankService, 2004);

        while (true) {
            server.run();
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        BankServerMonitor monitor = new BankServerMonitor();
        monitor.start();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (running) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ServerThread(clientSocket, bank, bankService));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        monitor.stopWork();

    }

}
