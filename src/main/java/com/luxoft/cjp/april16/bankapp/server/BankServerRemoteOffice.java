package com.luxoft.cjp.april16.bankapp.server;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.RORequest;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.service.BankService;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class BankServerRemoteOffice implements BankServerInterface {

    public BankServerRemoteOffice(Bank bank, BankService bankService) {
    }

    @Override
    public Response executeRequest(Request request) {
        RORequest atmRequest = (RORequest) request;

        throw new UnsupportedOperationException();
    }
}
