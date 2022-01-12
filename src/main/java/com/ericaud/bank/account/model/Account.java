package com.ericaud.bank.account.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private BigDecimal balance;
    public Account() {
        this.balance = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
