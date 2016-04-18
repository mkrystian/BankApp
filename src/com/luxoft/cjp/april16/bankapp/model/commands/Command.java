package com.luxoft.cjp.april16.bankapp.model.commands;

public interface Command {
    void execute();

    void printCommandInfo();
}