package com.luxoft.cjp.april16.bankapp.model;


/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-18.
 */
public enum AccountType {

    CHECKING_ACCOUNT("Checking account") {
        @Override
        public Account buildAccount(float account_balance, float account_overdraft, int account_id) {
            return new CheckingAccount(account_balance, account_overdraft, account_id);
        }
    },
    SAVING_ACCOUNT("Saving account") {
        @Override
        public Account buildAccount(float account_balance, float account_overdraft, int account_id) {
            return new SavingAccount(account_balance, account_id);
        }
    };

    private String name;

    AccountType(String s) {
        name = s;
    }


    public static AccountType factoryMethod(String accountType) {
        if (accountType.toLowerCase().equals("checking account") || accountType.toLowerCase().equals("c") || accountType.toLowerCase().equals("checkingaccount")) {
            return CHECKING_ACCOUNT;
        }
        if (accountType.toLowerCase().equals("saving account") || accountType.toLowerCase().equals("s") || accountType.toLowerCase().equals("savingaccount")) {
            return SAVING_ACCOUNT;
        }

        throw new IncorrectAccountTypeException(accountType + " is not correct account type.");
    }

    public abstract Account buildAccount(float account_balance, float account_overdraft, int account_id);

    @Override
    public String toString() {
        return name;
    }

    private static class IncorrectAccountTypeException extends RuntimeException {

        private String message;

        IncorrectAccountTypeException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }


}
