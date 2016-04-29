package com.luxoft.cjp.april16.bankapp.server;


import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.service.BankService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class Server implements Runnable {

    private ServerSocket Serversocket = null;
    private Bank bank;
    private BankService bankService;


    public Server(int portNumber, Bank bank, BankService bankService) {

        this.bank = bank;
        this.bankService = bankService;

        System.out.println("TEST");
        try {
            this.Serversocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {

        while (true) {

            Socket socket = null;
            Request request;
            Response message;
            try {
                socket = Serversocket.accept();
                System.out.println("Socked accepted");
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (!socket.isClosed()) {
                try (
                        OutputStream outputStream = socket.getOutputStream();
                        InputStream inputStream = socket.getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {


                    request = (Request) objectInputStream.readObject();

                    message = request.getIdentityType().getBankServer(bank, bankService)
                            .executeRequest(request);
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();


                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

