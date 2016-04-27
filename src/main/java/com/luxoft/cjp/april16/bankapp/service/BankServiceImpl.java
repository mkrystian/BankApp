package com.luxoft.cjp.april16.bankapp.service;

import com.luxoft.cjp.april16.bankapp.model.Account;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.exceptions.ClientExistsException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public class BankServiceImpl implements BankService {

    private String file = System.getProperty("user.dir") + "\\clients\\serialized\\serialized.ser";

    @Override
    public void addClient(Bank bank, Client client) {
        try {
            bank.addClient(client);
        } catch (ClientExistsException e) {
            System.out.println("Client: " + e.getClientName() + " already exists");
        }
    }

    @Override
    public void removeClient(Bank bank, Client client) {
        bank.removeClient(client);
    }

    @Override
    public void addAccount(Client client, Account account) {
        client.addAccount(account);
    }

    @Override
    public void setActiveAccount(Client client, Account account) {
        client.setActiveAccount(account);
    }

    @Override
    public Set<Account> getAccounts(Client client) {
        return client.getAccounts();
    }

    @Override
    public Set<Client> getClients(Bank bank) {
        return bank.getClients();
    }

    @Override
    public List<Client> getClientsByName(Bank bank, String name) {
        Map<String, List<Client>> map = bank.getClientsMap();
        List<Client> list = new ArrayList<>();

        map.entrySet().stream().filter(entry -> entry.getKey().toLowerCase().matches(".*" + name.toLowerCase() + ".*"))
                .forEach(entry -> list.addAll(entry.getValue()));

        return list;
    }

    @Override
    public void saveClient(Client client) {
        File outboundFile = new File(file);
        if (!outboundFile.exists()) {
            try {
                outboundFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(outboundFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client loadClient() {
        Client client = null;

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            client = (Client) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return client;
    }


}
