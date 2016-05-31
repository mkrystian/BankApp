package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.service.Report;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public class Client implements Report, Comparable, Serializable {

    private final Set<Account> accounts = new TreeSet<>();
    private final String pesel;
    private final String name;
    private final Gender gender;
    private final String email;
    private final String city;
    private int id = -1;
    private Account activeAccount;
    private float initialOverdraft = 0;

    public Client(String name, Gender gender, String pesel, float initialOverdraft, String email, String city) {
        this.pesel = pesel;
        this.name = name;
        this.gender = gender;
        this.initialOverdraft = initialOverdraft;
        this.email = email;
        this.city = city;
    }

    public Client(String name, Gender gender, String pesel, float initialOverdraft, String email, String city, int id) {
        this.pesel = pesel;
        this.name = name;
        this.gender = gender;
        this.initialOverdraft = initialOverdraft;
        this.email = email;
        this.city = city;
        this.id = id;
    }

    static Client factoryMethodForFeeds(Map<String, String> feed) {

        String pesel = feed.get("pesel");
        String name = feed.get("name");
        String gender = feed.get("gender");
        String initialOverdraft = feed.get("initialOverdraft");
        String email = feed.get("email");
        String city = feed.get("city");

        return new Client(name, Gender.factoryMethod(gender), pesel, Float.valueOf(initialOverdraft), email, city);
    }

    public String getPesel() {
        return pesel;
    }

    public String getName() {
        return name;
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

    public float getBalance() {
        //System.out.println(accounts);
        return activeAccount.getBalance();
    }

    public Set<Account> getAccounts() {
        return Collections.unmodifiableSet(accounts);
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
        return String.valueOf(id) + "|" + name + "|"
                + gender.getName() + "|" + pesel + "|"
                + initialOverdraft + "|" + email + "|"
                + city;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Gender getGender() {
        return gender;
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

    public void setId(int id) {
        this.id = id;
    }

    public int compareTo(Object o) {
        Client object2 = (Client) o;
        return id - object2.getId();
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

}
