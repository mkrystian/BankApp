package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;

/**
 * Created by KMajewski on 2016-04-12.
 */
public class SavingAccount extends AbstractAccount {

    public SavingAccount( float balance ){
        if( balance < 0 ) throw new IllegalArgumentException("Balance could not be negative in saving account");
        super.setBalance(balance);
    }

    @Override
    public void withdraw( float x) throws NotEnoughFoundsException{
        if( super.getBalance() > x ){
            super.setBalance( super.getBalance() - x );
        }else{
            throw new NotEnoughFoundsException( this, x, super.getBalance());
        }
    }

    @Override
    public void printReport(){
        System.out.println("Account type: Saving account");
        super.printReport();
    }
}
