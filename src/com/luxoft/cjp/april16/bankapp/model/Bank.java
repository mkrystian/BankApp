package com.luxoft.cjp.april16.bankapp.model;
import com.luxoft.cjp.april16.bankapp.model.exceptions.ClientExistsException;
import com.luxoft.cjp.april16.bankapp.service.Report;

import java.util.*;

/**
 * Created by KMajewski on 2016-04-12.
 */
public class Bank implements Report {

    private final List<Client> clients = new ArrayList<>();
    private final List<ClientRegistrationListener> clientRegistrationListeners = new ArrayList<ClientRegistrationListener>();

    {
        clientRegistrationListeners.add(new ClientRegistrationListener() {
            @Override
            public void onClientAdded(Client client) {
                StringBuilder output = new StringBuilder();
                output.append("*DebugLog | ")
                        .append( new Date() )
                        .append(" | Client: ")
                        .append(client.getName())
                        .append(" had been created*");
                System.out.println(output);
            }
        });
    }

    @Override
    public void printReport() {
        System.out.println("Bank report: ");
        for( Client val : clients) {
            System.out.println("==================================================================");
            val.printReport();
            System.out.println("==================================================================\n");
        }
    }

    public void addClient( Client client ) throws ClientExistsException{
        if(clients.contains(client)){
            throw new ClientExistsException(client);
        }
        clients.add(client);
        for ( ClientRegistrationListener val : clientRegistrationListeners) {
            val.onClientAdded(client);
        }
    }

    public void removeClient( Client client ){
        clients.remove(client);
    }

    public void addClientRegistrationListener( ClientRegistrationListener clientRegistrationListener){
        clientRegistrationListeners.add(clientRegistrationListener);
    }
    public void removeClientRegistrationListener( ClientRegistrationListener clientRegistrationListener){
        clientRegistrationListeners.remove(clientRegistrationListener);
    }

    /*public List<Client> getClients(){
        return Collections.unmodifiableList(clients);
    }*/

    /**
     * Created by KMajewski on 2016-04-13.
     */
    public class EmailNotyficationListener implements ClientRegistrationListener {
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

    /**
     * Created by KMajewski on 2016-04-13.
     */
    public interface ClientRegistrationListener {
        void onClientAdded(Client client);
    }
}
