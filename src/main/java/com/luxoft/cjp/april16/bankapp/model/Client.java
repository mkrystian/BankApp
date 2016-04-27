package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.service.Report;

import java.io.Serializable;
import java.util.*;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public class Client implements Report, Comparable, Serializable {

    private static int autoincrement = 0;
    private final Set<Account> accounts = new TreeSet<>();
    private int id = ++autoincrement;
    private String pesel;
    private String name;
    private Gender gender;
    private Account activeAccount;
    private float initialOverdraft = 0;
    private String email;
    private String city;
    public Client(String name, Gender gender, String pesel, float initialOverdraft, String email, String city) {
        this.pesel = pesel;
        this.name = name;
        this.gender = gender;
        this.initialOverdraft = initialOverdraft;
        this.email = email;
        this.city = city;
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

    String getPesel() {
        return pesel;
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

    public int compareTo(Object o) {
        Client object2 = (Client) o;
        return id - object2.getId();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
