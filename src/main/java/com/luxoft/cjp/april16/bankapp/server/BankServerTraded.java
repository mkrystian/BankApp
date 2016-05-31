package com.luxoft.cjp.april16.bankapp.server;

import com.luxoft.cjp.april16.bankapp.BankApplication;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-09.
 */
class BankServerTraded implements Runnable {

    private static final int POOL_SIZE = 100;
    private static final Logger log = LoggerFactory.getLogger(BankServerTraded.class);
    private final Bank bank;
    private final BankService bankService;
    private final int port;
    private final ExecutorService pool;
    @SuppressWarnings("FieldCanBeLocal")
    private boolean running;
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

        //noinspection InfiniteLoopStatement
        while (true) {
            server.run();
        }
    }

    public void run() {
        log.info("Server started on port: {}", port);
        BankServerMonitor monitor = new BankServerMonitor();
        monitor.start();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            running = true;
            while (running) {
                Socket clientSocket = serverSocket.accept();
                log.info("Request received from address: {}", clientSocket.getInetAddress());
                pool.execute(new ServerThread(clientSocket, bank, bankService));

            }
        } catch (IOException e) {
            log.error("Server connection error:\n", e);
            System.exit(-1);
        }
        monitor.stopWork();

    }

}
