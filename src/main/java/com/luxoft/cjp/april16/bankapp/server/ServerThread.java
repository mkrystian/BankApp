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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-09.
 */
class ServerThread implements Runnable {

    private static final AtomicInteger counter = new AtomicInteger(0);
    private final Socket clientSocket;
    private final Bank bank;
    private final BankService bankService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Request message;

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
            logger.log(Level.SEVERE, "Cannot open streams: ", e);
        }

        try {
            try {
                message = (Request) in.readObject();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Cannot read object from stream: " + e.getMessage(), e);
            }
            BankServerInterface bankServer = message.getIdentityType().getBankServer(bank, bankService);
            sendMessage(bankServer.executeRequest(message));
        } catch (ClassNotFoundException classNotFoundException) {
            logger.log(Level.SEVERE, "Data received in unknown format");
            System.exit(-1);
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException ioException) {
                logger.log(Level.SEVERE, "Cannot close connection: ", ioException);
                System.exit(-1);
            }
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Cannot close client socket: ", e);
            System.exit(-1);

        }
        counter.decrementAndGet();
    }

    private void sendMessage(final Response response) {
        try {
            out.writeObject(response);
            out.flush();
            logger.info("Response sent to client: " + clientSocket.getInetAddress());
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "Error during sending message: ", ioException);
            System.exit(-1);
        }
    }
}
