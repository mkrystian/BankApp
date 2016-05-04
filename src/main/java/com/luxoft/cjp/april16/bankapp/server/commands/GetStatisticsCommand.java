package com.luxoft.cjp.april16.bankapp.server.commands;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.RORequest;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.ResponseType;
import com.luxoft.cjp.april16.bankapp.service.BankReport;
import com.luxoft.cjp.april16.bankapp.service.BankService;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-04.
 */
public class GetStatisticsCommand extends AbstractRemoteOfficeCommand {
    public GetStatisticsCommand(Bank bank, BankService bankService) {
        super(bank, bankService);
    }

    @Override
    public Response execute(RORequest request) {
        Response response = new Response();
        response.setType(ResponseType.SUCCESS);
        BankReport bankReport = new BankReport();
        response.setMessage(bankReport.getReport(bank));
        return response;
    }
}
