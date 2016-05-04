package com.luxoft.cjp.april16.bankapp.server;

import com.luxoft.cjp.april16.bankapp.BankApplication;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {
    ServerSocket providerSocket;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    Request message;
    private Bank bank;
    private BankService bankService;
    private int port;

    public BankServer(Bank bank, BankService bankService, int port) {
        this.bank = bank;
        this.bankService = bankService;
        this.port = port;
    }

    public static void main(final String args[]) {
        BankService bankService = new BankServiceImpl();
        Bank bank = new Bank("TestBank");
        BankApplication.initialize(bank, bankService);
        BankServer server = new BankServer(bank, bankService, 2004);
        while (true) {
            server.run();
        }
    }

    void run() {
        try {
            // 1. creating a server socket
            providerSocket = new ServerSocket(port, 10);
            // 2. Wait for connection
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            System.out.println("Connection received from "
                    + connection.getInetAddress().getHostName());
            // 3. get Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            //sendMessage("Connection successful");
            // 4. The two parts communicate via the input and output streams

            try {
                message = (Request) in.readObject();
                BankServerInterface bankServer = message.getIdentityType().getBankServer(bank, bankService);
                sendMessage(bankServer.executeRequest(message));
            } catch (ClassNotFoundException classNotFoundException) {
                System.err.println("Data received in unknown format");
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            // 4: Closing connection
            try {
                in.close();
                out.close();
                providerSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    void sendMessage(final Response response) {
        try {
            out.writeObject(response);
            out.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}