package com.luxoft.cjp.april16.bankapp.service;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.service.exceptions.FeedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-27.
 */
public class BankFeedServiceImpl implements BankFeedService {

    private String directory;
    private Bank bank;

    public BankFeedServiceImpl(Bank bank, String directory) {
        this.bank = bank;
        this.directory = directory;
    }

    public BankFeedServiceImpl(Bank bank) {
        this.bank = bank;
        this.directory = System.getProperty("user.dir") + "\\clients";
    }

    private List<File> readFilesList() {
        List<File> list = new ArrayList<>();
        File inboundFile = new File(this.directory);
        String[] files;
        if (inboundFile.isFile()) {
            list.add(inboundFile);
        } else {
            files = inboundFile.list();
            for (String file : files) {
                File newFile = new File(directory + "\\" + file);
                if (newFile.isFile()) {
                    list.add(newFile);
                }
            }
        }
        return list;
    }

    @Override
    public void loadFeed() {
        List<File> filesList = readFilesList();

        for (File file : filesList) {
            loadFileToBank(file);
        }

        System.out.println(filesList);

    }

    private void loadFileToBank(File file) {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String stringFeed = "";

            while ((stringFeed = bufferedReader.readLine()) != null && stringFeed != "") {
                bank.parseFeed(parseStringFeed(stringFeed));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ParsedKeyValuePair parseKeyValuePair(String element) {
        Pattern pattern = Pattern.compile("(.*)=(.*)");
        Matcher matcher = pattern.matcher(element);
        ParsedKeyValuePair keyValuePair = new ParsedKeyValuePair();
        if (matcher.find()) {
            keyValuePair.key = matcher.group(1);
            keyValuePair.value = matcher.group(2);
        } else {
            throw new FeedException("Incorrect pair key=value: " + element);
        }
        return keyValuePair;
    }

    private Map<String, String> parseStringFeed(String stringFeed) {
        Map<String, String> map = new HashMap<>();
        String[] args = stringFeed.split(";");
        for (String element : args) {
            addToMap(map, parseKeyValuePair(element));
        }
        System.out.println(Arrays.toString(args));
        return map;
    }

    private void addToMap(Map<String, String> map, ParsedKeyValuePair keyValuePair) {
        if (map.containsKey(keyValuePair.key)) {
            throw new FeedException("Key duplication in one feed: " + keyValuePair.key);
        } else {
            map.put(keyValuePair.key, keyValuePair.value);
        }
    }

    private class ParsedKeyValuePair {
        String key;
        String value;
    }
}
