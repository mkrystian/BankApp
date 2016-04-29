package com.luxoft.cjp.april16.bankapp.server.commands;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequest;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.ResponseType;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.exceptions.ClientNotFoundByPeselException;

public class WithdrawCommand extends AbstractATMCommand {


    public WithdrawCommand(Bank bank, BankService bankService) {
        super(bank, bankService);
    }

    @Override
    public Response execute(ATMRequest request) {
        Response newResponse = new Response();
        Client client;
        Float amount = Float.valueOf(request.getData()[1]);
        try {
            client = super.getClient(request);
            client.getActiveAccount().withdraw(amount);
            newResponse.setType(ResponseType.SUCCESS);
            newResponse.setMessage("Withdrawn: " + Float.toString(amount));
        } catch (ClientNotFoundByPeselException | NotEnoughFoundsException e) {
            newResponse.setType(ResponseType.FAIL);
            newResponse.setMessage(e.getMessage());
        }

        return newResponse;
    }
}