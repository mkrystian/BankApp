package com.luxoft.cjp.april16.bankapp.atm;

import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityCard;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

abstract class BankClient {
    private final String SERVER;
    private final int port;
    IdentityCard identityCard;
    private Socket requestSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    BankClient(String SERVER, int port, IdentityCard identityCard) {
        this.SERVER = SERVER;
        this.port = port;
        this.identityCard = identityCard;
    }

    private void establishConnection() {
        try {
            // 1. creating a socket to connect to the server
            requestSocket = new Socket(SERVER, 2004);
            System.out.println("Connected to localhost in port 2004");
            // 2. get Input and Output streams
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            in.close();
            out.close();
            requestSocket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    private Response readResponse() {
        Response message = null;
        try {
            message = (Response) in.readObject();
        } catch (ClassNotFoundException classNot) {
            System.err.println("data received in unknown format");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    private void sendMessage(final Request request) {
        try {
            out.writeObject(request);
            out.flush();
            //System.out.println("client>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    Response sendRequest(Request request) {
        establishConnection();
        sendMessage(request);
        Response response = readResponse();
        closeConnection();
        return response;
    }

}