package com.luxoft.cjp.april16.bankapp.server;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequest;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.service.BankService;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class BankServerATM implements BankServerInterface {

    private Bank bank;
    private BankService bankService;

    public BankServerATM(Bank bank, BankService bankService) {
        this.bank = bank;
        this.bankService = bankService;
    }


    @Override
    public Response executeRequest(Request request) {
        ATMRequest atmRequest = (ATMRequest) request;
        return atmRequest.getRequestType().getCommand(bank, bankService).execute(atmRequest);
    }








}
