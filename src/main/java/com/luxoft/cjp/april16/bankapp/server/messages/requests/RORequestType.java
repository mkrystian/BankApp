package com.luxoft.cjp.april16.bankapp.server.messages.requests;

import java.io.Serializable;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public enum RORequestType implements Serializable {
    GETSTATISTICS,
    FINDCLIENTBYNAME,
    ADDCLIENT,
    REMOVECLIENT
}
