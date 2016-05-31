package com.luxoft.cjp.april16.bankapp.service;


import com.luxoft.cjp.april16.bankapp.model.Account;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-19.
 */
public class BankReport {

    int getNumberOfClients(Bank bank) {
        return bank.getClients().size();
    }

    int getAccountsNumber(Bank bank) {
        int accountsNumbers = 0;
        for (Client client : bank.getClients()) {
            accountsNumbers += client.getAccounts().size();
        }
        return accountsNumbers;
    }

    float getBankCreditSum(Bank bank) {
        float creditSum = 0;
        for (Client client : bank.getClients()) {
            for (Account account : client.getAccounts()) {
                if (account.getBalance() < 0) creditSum -= account.getBalance();
            }
        }
        return creditSum;
    }

    Map<String, List<Client>> getClientsByCity(Bank bank) {
        Map<String, List<Client>> map = new TreeMap<>();
        //BankApplication.initialize( bank , new BankServiceImpl());
        for (Client client : bank.getClients()) {
            if (!map.containsKey(client.getCity())) {
                ArrayList<Client> tmpArrayList = new ArrayList<>();
                tmpArrayList.add(client);
                map.put(client.getCity(), tmpArrayList);
            } else map.get(client.getCity()).add(client);
        }

        return map;
    }

    public void printReport() {
        System.out.println("Bank Report: \nAccounts number: " +
                getAccountsNumber(com.luxoft.cjp.april16.bankapp.BankApplication.bank) + "\nBank credit sum: " +
                getBankCreditSum(com.luxoft.cjp.april16.bankapp.BankApplication.bank) + "\nNumber of clients in particular cities: ");

        for (Map.Entry<String, List<Client>> entrySet : getClientsByCity(com.luxoft.cjp.april16.bankapp.BankApplication.bank).entrySet()) {
            System.out.println(entrySet.getKey() + " : " + entrySet.getValue().size());
        }
    }

    public String getReport(Bank bank) {
        StringBuilder output = new StringBuilder("Bank Report: \nAccounts number: " +
                getAccountsNumber(bank) + "\nBank credit sum: " +
                getBankCreditSum(bank) + "\nNumber of clients in particular cities: \n");


        for (Map.Entry<String, List<Client>> entrySet : getClientsByCity(bank).entrySet()) {
            output.append(entrySet.getKey()).append(" : ").append(entrySet.getValue().size()).append("\n");
        }
        return output.toString();
    }
}
