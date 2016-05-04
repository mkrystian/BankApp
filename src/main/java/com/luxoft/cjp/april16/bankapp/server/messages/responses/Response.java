package com.luxoft.cjp.april16.bankapp.server.messages.responses;

import java.io.Serializable;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class Response implements Serializable {

    private ResponseType type;
    private String message;
    private boolean closeConnection = false;


    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCloseConnection() {
        return closeConnection;
    }

    public void setCloseConnection(boolean closeConnection) {
        this.closeConnection = closeConnection;
    }
}
