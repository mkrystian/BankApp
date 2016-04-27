package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
abstract class AbstractAccount implements Account {

    private static int autoincrement = 0;
    private static Map<String, AccountFactoryCommand> classMap = new HashMap<>();

    static {
        classMap.put("c", new CheckingAccountFactoryMethodCommandImpl());
        classMap.put("s", new SavingAccountFactoryMethodCommandImpl());

    }

    private int id = ++autoincrement;
    private float balance;

    AbstractAccount(float balance) {
        if (balance < 0) throw new IllegalArgumentException("Balance could not be negative in saving account");
        this.balance = balance;
    }

    // Runs different factory method depends on value in account type
    static Account factoryMethodForFeed(Map<String, String> feed) {
        //System.out.println(classMap.containsKey("c")) ;
        return classMap.get(feed.get("account")).execute(feed);
    }

    @Override
    public float getBalance() {
        return balance;
    }

    void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(float x) {
        setBalance(balance + x);
    }

    @Override
    public void printReport() {
        System.out.println("Balance: " + getBalance());
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAccount)) return false;

        AbstractAccount that = (AbstractAccount) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public BigDecimal decimalValue() {
        return new BigDecimal(Math.round(balance));
    }

    @Override
    public String toString() {
        return id + "|" + balance;
    }

    @Override
    public void transferTo(Account account, float amount) throws NotEnoughFoundsException {
            this.withdraw(amount);
            account.deposit(amount);
    }

    private interface AccountFactoryCommand {
        Account execute(Map<String, String> feed);
    }

    private static class CheckingAccountFactoryMethodCommandImpl implements AccountFactoryCommand {

        @Override
        public Account execute(Map<String, String> feed) {
            return CheckingAccount.checkingAccountFactoryMethodForFeed(feed);
        }
    }

    private static class SavingAccountFactoryMethodCommandImpl implements AccountFactoryCommand {

        @Override
        public Account execute(Map<String, String> feed) {
            return SavingAccount.savingAccountFactoryMethodForFeed(feed);
        }
    }

    public int compareTo( Object object){
        return this.getId() - ( (AbstractAccount) object ).getId();
    }

}
