package com.luxoft.cjp.april16.bankapp.atm;

import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityCard;
import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityType;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.RORequest;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.RORequestType;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-04.
 */
public class RemoteOfficeClient extends BankClient {
    public RemoteOfficeClient(String SERVER, int port, IdentityCard identityCard) {
        super(SERVER, port, identityCard);
    }

    public static void main(String[] Args) {
        RemoteOfficeClient remoteOfficeClient = new RemoteOfficeClient("localhost", 2004, new IdentityCard(IdentityType.REMOTE_OFFICE, "Test Remote Office"));
        System.out.println(remoteOfficeClient.getStatistics());
        System.out.println(remoteOfficeClient.findClientByName("John"));
        System.out.println(remoteOfficeClient.removeClient("78041298513"));
        System.out.println(remoteOfficeClient.getStatistics());
    }

    String getStatistics() {
        Request request = new RORequest(identityCard, RORequestType.GET_STATISTICS);
        return sendRequest(request).getMessage();
    }

    String findClientByName(String name) {
        Request request = new RORequest(identityCard, RORequestType.FIND_CLIENT_BY_NAME);
        request.setData(new String[1]);
        request.getData()[0] = name;
        return sendRequest(request).getMessage();
    }

    String removeClient(String pesel) {
        Request request = new RORequest(identityCard, RORequestType.REMOVE_CLIENT);
        request.setData(new String[1]);
        request.getData()[0] = pesel;
        return sendRequest(request).getMessage();
    }
}
