package com.ericaud.bank.account.service;

import com.ericaud.bank.account.exception.InvalidOperationException;
import com.ericaud.bank.account.model.Account;
import com.ericaud.bank.account.model.Operation;
import com.ericaud.bank.account.model.OperationType;

import java.math.BigDecimal;

public class AccountManager {

    private Account account;

    public AccountManager(Account account) {
        this.account = account;
    }

    public void doDeposit(double amount) throws InvalidOperationException {
        doOperation(amount,OperationType.DEPOSIT);
    }

    private synchronized void doOperation(double amount, OperationType operationType) throws InvalidOperationException {
        Operation operation = new Operation(operationType, BigDecimal.valueOf(amount));
        if (operationType.equals(OperationType.DEPOSIT)) {
            this.account.setBalance(this.account.getBalance().add(BigDecimal.valueOf(amount)));
        }
    }

    public Account getAccount() {
        return account;
    }

}
