package com.luxoft.cjp.april16.bankapp.model.exceptions;

import com.luxoft.cjp.april16.bankapp.model.Account;

/**
 * Created by KMajewski on 2016-04-13.
 */
public class OverDraftLimitExceededException extends NotEnoughFoundsException {

    private final float overdraft;

    public OverDraftLimitExceededException(Account account, float amount, float balance, float overdraft) {
        super(account, amount, balance);
        this.overdraft = overdraft;
    }

    public float getOverdraft() {
        return overdraft;
    }

    @Override
    public String getInfo(){
        StringBuilder output = new StringBuilder();
        output.append(super.getInfo()).append(" | Overdraft: ").append(overdraft);

        return output.toString();
    }
}
