package com.luxoft.cjp.april16.bankapp.server.messages.requests;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class RORequest extends Request {


    RORequestType requestType;

    public RORequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RORequestType requestType) {
        this.requestType = requestType;
    }
}
