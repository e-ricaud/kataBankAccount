package com.ericaud.kata.bankaccount.controller.model;

import com.ericaud.kata.bankaccount.entity.Account;

public class OperationForm {

    private final Account account;
    private final double amount;

    public OperationForm(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }
}
