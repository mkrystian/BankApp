package com.luxoft.cjp.april16.bankapp.server;


import com.luxoft.cjp.april16.bankapp.server.messages.requests.Request;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public interface BankServerInterface {
    Response executeRequest(Request request);
}
