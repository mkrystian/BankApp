package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.model.exceptions.ClientExistsException;
import com.luxoft.cjp.april16.bankapp.service.Report;

import java.util.*;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public class Bank implements Report {

    private static int autoincrement = 0;
    private final Set<Client> clients = new HashSet<>();
    private final List<ClientRegistrationListener> clientRegistrationListeners = new ArrayList<>();
    private final Map<String, List<Client>> clientsMap = new HashMap<>();
    private int id = ++autoincrement;
    private String name;

    {
        clientRegistrationListeners.add(new ClientRegistrationListener() {
            @Override
            public void onClientAdded(Client client) {
                StringBuilder output = new StringBuilder();
                output.append("*DebugLog | ")
                        .append(new Date())
                        .append(" | Client: ")
                        .append(client.getName())
                        .append(" had been created*");
                System.out.println(output);
            }
        });
        clientRegistrationListeners.add(new MapAddListener());
    }

    public Bank(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bank)) return false;

        Bank bank = (Bank) o;

        return id == bank.id && name.equals(bank.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public void printReport() {
        System.out.println("Bank report: ");
        for (Client val : clients) {
            System.out.println("==================================================================");
            val.printReport();
            System.out.println("==================================================================\n");
        }
    }

    public void addClient(Client client) throws ClientExistsException {
        if (clients.contains(client)) {
            throw new ClientExistsException(client);
        }
        clients.add(client);
        for (ClientRegistrationListener val : clientRegistrationListeners) {
            val.onClientAdded(client);
        }
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void addClientRegistrationListener(ClientRegistrationListener clientRegistrationListener) {
        clientRegistrationListeners.add(clientRegistrationListener);
    }

    public void removeClientRegistrationListener(ClientRegistrationListener clientRegistrationListener) {
        clientRegistrationListeners.remove(clientRegistrationListener);
    }

    public Set<Client> getClients() {
        return Collections.unmodifiableSet(clients);
    }

    public Map<String, List<Client>> getClientsMap() {
        return Collections.unmodifiableMap(clientsMap);
    }


    public interface ClientRegistrationListener {
        void onClientAdded(Client client);
    }


    public class EmailNotificationListener implements ClientRegistrationListener {
        @Override
        public void onClientAdded(Client client) {
            StringBuilder output = new StringBuilder();
            output.append("#Event listener EmailClientListener| Client: ")
                    .append(client.getName())
                    .append(" had been created#");
            System.out.println(output);
        }
    }

    /**
     * Created by KMajewski on 2016-04-13.
     */
    public class PrintClientListener implements ClientRegistrationListener {
        @Override
        public void onClientAdded(Client client) {
            StringBuilder output = new StringBuilder();
            output.append("#Event listener PrintClientListener| Client: ")
                    .append(client.getName())
                    .append(" had been created#");
            System.out.println(output);
        }
    }

    private class MapAddListener implements ClientRegistrationListener {

        @Override
        public void onClientAdded(Client client) {
            if (!clientsMap.containsKey(client.getName())) {
                List<Client> newList = new ArrayList<>();
                newList.add(client);
                clientsMap.put(client.getName(), newList);
            } else {
                clientsMap.get(client.getName()).add(client);
            }
        }
    }
}