package com.luxoft.cjp.april16.bankapp.atm;

import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityCard;
import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityType;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequest;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequestType;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-04.
 */
class ATMClient extends BankClient {
    private ATMClient(IdentityCard identityCard) {
        super(2004, identityCard);
    }

    public static void main(String[] Args) {
        ATMClient atmClient = new ATMClient(new IdentityCard(IdentityType.ATM));
        atmClient.testGetBalance();
        atmClient.testWithdraw();
        atmClient.testGetBalance();
    }

    private void testGetBalance() {
        String pesel = "78021298512";
        Request request = new ATMRequest(identityCard, ATMRequestType.GET_BALANCE);
        request.setData(new String[1]);
        request.getData()[0] = pesel;

        System.out.println(sendRequest(request).getMessage());
    }

    private void testWithdraw() {
        String pesel = "78021298512";
        Request request = new ATMRequest(identityCard, ATMRequestType.WITHDRAW);
        request.setData(new String[2]);
        request.getData()[0] = pesel;
        request.getData()[1] = String.valueOf(500);

        System.out.println(sendRequest(request).getMessage());
    }
}
