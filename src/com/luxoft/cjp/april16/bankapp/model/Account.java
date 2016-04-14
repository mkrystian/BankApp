package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;
import com.luxoft.cjp.april16.bankapp.service.Report;

/**
 * Created by KMajewski on 2016-04-12.
 */
public interface Account extends Report {
    float getBalance();
    void deposit( float x);
    void withdraw( float x) throws NotEnoughFoundsException;
}
