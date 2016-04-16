package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.service.Report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public class Client implements Report {

    private final List<Account> accounts = new ArrayList<>();
    private String name;
    private Gender gender;
    private Account activeAccount;
    private float initialOverdraft = 0;


    public Client(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public Client(String name, Gender gender, float initialOverdraft) {
        this.name = name;
        this.gender = gender;
        this.initialOverdraft = initialOverdraft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(Account activeAccount) {
        this.activeAccount = activeAccount;
    }

    public float getInitialOverdraft() {
        return initialOverdraft;
    }

    public void setInitialOverdraft(float initialOverdraft) {
        this.initialOverdraft = initialOverdraft;
    }

    public float getBalance() {
        return activeAccount.getBalance();
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    @Override
    public void printReport() {
        System.out.println("Client name: " + gender.getSalutation() + name);
        System.out.println("Overdraft: " + initialOverdraft);
        System.out.println("Accounts:");
        System.out.println("------------------------------------------------------------------");
        accounts.forEach(Account::printReport);
        System.out.println("------------------------------------------------------------------");
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
