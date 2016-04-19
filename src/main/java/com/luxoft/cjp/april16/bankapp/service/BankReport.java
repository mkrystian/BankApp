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
public class BankReport implements Report {

    public int getNumberOfClients(Bank bank) {
        return bank.getClients().size();
    }

    public int getAccountsNumber(Bank bank) {
        int accountsNumbers = 0;
        for (Client client : bank.getClients()) {
            accountsNumbers += client.getAccounts().size();
        }
        return accountsNumbers;
    }

    public float getBankCreditSum(Bank bank) {
        float creditSum = 0;
        for (Client client : bank.getClients()) {
            for (Account account : client.getAccounts()) {
                if (account.getBalance() < 0) creditSum -= account.getBalance();
            }
        }
        return creditSum;
    }

    public Map<String, List<Client>> getClientsByCity(Bank bank) {
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

    @Override
    public void printReport() {

    }
}
