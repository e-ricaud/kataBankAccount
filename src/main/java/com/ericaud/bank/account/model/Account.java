package com.ericaud.bank.account.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private BigDecimal balance;
    private List<Operation> operations;

    public Account() {
        this.balance = BigDecimal.ZERO;
        this.operations = new ArrayList<>();
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }

}
