package com.luxoft.cjp.april16.bankapp.server;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.service.BankService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-09.
 */
class ServerThread implements Runnable {

    private static AtomicInteger counter = new AtomicInteger(0);
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Request message;
    private Bank bank;
    private BankService bankService;

    ServerThread(Socket clientSocket, Bank bank, BankService bankService) {
        this.clientSocket = clientSocket;
        counter.incrementAndGet();
        this.bank = bank;
        this.bankService = bankService;

    }

    static int getCounter() {
        return counter.get();
    }

    @Override
    public void run() {

        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            try {
                message = (Request) in.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BankServerInterface bankServer = message.getIdentityType().getBankServer(bank, bankService);
            sendMessage(bankServer.executeRequest(message));
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Data received in unknown format");
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        counter.decrementAndGet();
    }

    private void sendMessage(final Response response) {
        try {
            out.writeObject(response);
            out.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
