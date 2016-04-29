package com.luxoft.cjp.april16.bankapp.atm;

import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityCard;
import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityType;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequest;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequestType;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;

import java.io.*;
import java.net.Socket;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class ATM {

    private static IdentityCard identityCard = new IdentityCard(IdentityType.ATM, "TestATM");


    public static void main(String[] Args) {

        Socket socket = null;
        try {
            socket = new Socket("localhost", 4231);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();

             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

            ATMRequest request = new ATMRequest();

            request.setIdentityCard(identityCard);
            String[] data = new String[1];
            data[0] = "78021298512";
            request.setRequestType(ATMRequestType.GET_BALANCE);
            request.setData(data);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            data = new String[2];
            data[0] = "78021298512";
            data[1] = "200";
            request.setData(data);
            request.setRequestType(ATMRequestType.WITHDRAW);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();


            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            System.out.println(((Response) objectInputStream.readObject()).getMessage());
            System.out.println(((Response) objectInputStream.readObject()).getMessage());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
