package com.luxoft.cjp.april16.bankapp.atm;

import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.Gender;
import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityCard;
import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityType;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequest;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequestType;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;

import java.util.concurrent.Callable;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-04.
 */
public class BankClientMock extends BankClient implements Callable<Long> {

    private String pesel;

    public BankClientMock(String SERVER, int port, IdentityCard identityCard, Client client) {
        super(SERVER, port, identityCard);
        this.pesel = client.getPesel();
    }


    public static void main(String[] Args) {
        Client client = new Client("Mock Client", Gender.MALE, "78021298512", 2000, "mock@mail.com", "Mock");

        BankClientMock bankClientMock = new BankClientMock("localhost", 2004, new IdentityCard(IdentityType.ATM, "Test ATM"), client);
        bankClientMock.withdraw();
    }

    private void withdraw() {
        Request request = new ATMRequest(identityCard, ATMRequestType.WITHDRAW);
        request.setData(new String[2]);
        request.getData()[0] = pesel;
        request.getData()[1] = String.valueOf(1);

        sendRequest(request);

    }


    public void run() {
        withdraw();
    }

    @Override
    public Long call() throws Exception {
        Long currentTime = System.currentTimeMillis();
        withdraw();
        return System.currentTimeMillis() - currentTime;
    }
}
