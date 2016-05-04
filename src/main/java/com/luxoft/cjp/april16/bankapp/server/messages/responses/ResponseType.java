package com.luxoft.cjp.april16.bankapp.server.messages.responses;

import java.io.Serializable;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public enum ResponseType implements Serializable {
    SUCCESS,
    FAIL,
    CLOSE_CONNECTION
}
