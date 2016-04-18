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

    private static int autoincrement = 0;
    private final List<Account> accounts = new ArrayList<>();
    private int id = ++autoincrement;
    private String pesel;
    private String name;
    private Gender gender;
    private Account activeAccount;
    private float initialOverdraft = 0;


    public Client(String name, Gender gender, String pesel) {
        this.name = name;
        this.gender = gender;
        this.pesel = pesel;
    }

    public Client(String name, Gender gender, String pesel, float initialOverdraft) {
        this.name = name;
        this.gender = gender;
        this.pesel = pesel;
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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        //TODO: add to string method

        return output.toString();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        return pesel.equals(client.pesel);

    }

    @Override
    public int hashCode() {
        return pesel.hashCode();
    }

    public int getId() {
        return id;
    }
}
