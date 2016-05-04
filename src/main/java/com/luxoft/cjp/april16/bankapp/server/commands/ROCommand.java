package com.luxoft.cjp.april16.bankapp.server.commands;

import com.luxoft.cjp.april16.bankapp.server.messages.requests.RORequest;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-04.
 */
interface ROCommand {
    Response execute(RORequest request);
}
