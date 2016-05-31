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
class RemoteOfficeClient extends BankClient {
    private RemoteOfficeClient(IdentityCard identityCard) {
        super(2004, identityCard);
    }

    public static void main(String[] Args) {
        RemoteOfficeClient remoteOfficeClient = new RemoteOfficeClient(new IdentityCard(IdentityType.REMOTE_OFFICE));
        System.out.println(remoteOfficeClient.getStatistics());
        System.out.println(remoteOfficeClient.findClientByName());
        System.out.println(remoteOfficeClient.removeClient());
        System.out.println(remoteOfficeClient.getStatistics());
    }

    private String getStatistics() {
        Request request = new RORequest(identityCard, RORequestType.GET_STATISTICS);
        return sendRequest(request).getMessage();
    }

    private String findClientByName() {
        Request request = new RORequest(identityCard, RORequestType.FIND_CLIENT_BY_NAME);
        request.setData(new String[1]);
        request.getData()[0] = "John";
        return sendRequest(request).getMessage();
    }

    private String removeClient() {
        Request request = new RORequest(identityCard, RORequestType.REMOVE_CLIENT);
        request.setData(new String[1]);
        request.getData()[0] = "78041298513";
        return sendRequest(request).getMessage();
    }
}
