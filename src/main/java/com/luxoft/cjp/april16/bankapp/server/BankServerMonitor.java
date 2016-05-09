package com.luxoft.cjp.april16.bankapp.server;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-09.
 */
public class BankServerMonitor extends Thread {

    private boolean running = true;


    public void run() {
        while (running) {
            System.out.println("Active users: " + ServerThread.getCounter());
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopWork() {
        running = false;
    }
}
