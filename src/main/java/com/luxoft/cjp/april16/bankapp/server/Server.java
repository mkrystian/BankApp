package com.luxoft.cjp.april16.bankapp.server;


import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityType;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.service.BankService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class Server implements Runnable {

    private ServerSocket Serversocket = null;
    private Bank bank;
    private BankService bankService;
    private Map<IdentityType, BankServerInterface> bankServerMap = createBankServerMap();


    public Server(int portNumber, Bank bank, BankService bankService) {

        this.bank = bank;
        this.bankService = bankService;

        createBankServerMap();
        try {
            this.Serversocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<IdentityType, BankServerInterface> createBankServerMap() {
        Map<IdentityType, BankServerInterface> map = new HashMap<>();
        map.put(IdentityType.ATM, new BankServerATM(bank, bankService));
        map.put(IdentityType.REMOTE_OFFICE, new BankServerRemoteOffice(bank, bankService));
        return map;
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

            try (
                    OutputStream outputStream = socket.getOutputStream();
                    InputStream inputStream = socket.getInputStream();
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

                request = (Request) objectInputStream.readObject();
                message = bankServerMap.get(request.getIdentityType()).executeRequest(request);
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

