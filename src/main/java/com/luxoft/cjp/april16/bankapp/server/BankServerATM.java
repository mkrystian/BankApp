package com.luxoft.cjp.april16.bankapp.server;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequest;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequestType;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.ResponseType;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.exceptions.ClientNotFoundByPeselException;

import java.util.HashMap;
import java.util.Map;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class BankServerATM implements BankServerInterface {

    private Map<ATMRequestType, ATMCommand> commandMap = new HashMap<>();

    private Bank bank;
    private BankService bankService;

    public BankServerATM(Bank bank, BankService bankService) {
        this.bank = bank;
        this.bankService = bankService;

        commandMap.put(ATMRequestType.GETBALANCE, new GetBalanceCommand());
        commandMap.put(ATMRequestType.WITHDRW, new WithdrawCommand());
    }

    @Override
    public Response executeRequest(Request request) {
        ATMRequest atmRequest = (ATMRequest) request;

        throw new UnsupportedOperationException();
    }

    private interface ATMCommand {
        Response execute(ATMRequest request);
    }

    private abstract class AbstractATMCommand implements ATMCommand {
        private String pesel;

        public Client getClient(ATMRequest request) throws ClientNotFoundByPeselException {
            pesel = request.getData()[0];
            Client client = null;

            client = bankService.getClientByPesel(bank, pesel);

            return client;
        }
    }

    private class GetBalanceCommand extends AbstractATMCommand {

        @Override
        public Response execute(ATMRequest request) {

            Response newResponse = new Response();
            Client client = null;
            try {
                client = super.getClient(request);
                newResponse.setType(ResponseType.SUCCESS);
                newResponse.setMessage(Float.toString(client.getBalance()));
            } catch (ClientNotFoundByPeselException e) {
                newResponse.setType(ResponseType.FAIL);
                newResponse.setMessage(e.getMessage());
            }


            return newResponse;
        }
    }

    private class WithdrawCommand extends AbstractATMCommand {


        @Override
        public Response execute(ATMRequest request) {
            Response newResponse = new Response();
            Client client = null;
            Float amount = Float.valueOf(request.getData()[1]);
            try {
                client = super.getClient(request);
                client.getActiveAccount().withdraw(amount);
            } catch (ClientNotFoundByPeselException e) {
                newResponse.setType(ResponseType.FAIL);
                newResponse.setMessage(e.getMessage());
            } catch (NotEnoughFoundsException e) {
                newResponse.setType(ResponseType.FAIL);
                newResponse.setMessage(e.toString());
            }

            return newResponse;


        }
    }
}
