package com.ericaud.kata.bankaccount.services;


import com.ericaud.kata.bankaccount.entity.Account;
import com.ericaud.kata.bankaccount.entity.Operation;
import com.ericaud.kata.bankaccount.entity.OperationType;
import com.ericaud.kata.bankaccount.exception.InvalidOperationException;
import com.ericaud.kata.bankaccount.persistance.AccountRepository;
import com.ericaud.kata.bankaccount.persistance.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountManager {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationRepository operationRepository;

    public void doDeposit(double amount, Account account) throws InvalidOperationException {
        doOperation(amount, OperationType.DEPOSIT, account);
    }

    public void doWithdraw(double amount, Account account) throws InvalidOperationException {
        doOperation(amount,OperationType.WITHDRAWAL, account);
    }

    private synchronized void doOperation(double amount, OperationType operationType, Account account) throws InvalidOperationException {
        Operation operation = new Operation(operationType, BigDecimal.valueOf(amount), account);
        if (operationType.equals(OperationType.DEPOSIT)) {
            account.setBalance(account.getBalance().add(BigDecimal.valueOf(amount)));
            this.accountRepository.save(account);
            this.operationRepository.save(operation);
            account.addOperation(operation);
        } else if (operationType.equals(OperationType.WITHDRAWAL)) {
            account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(amount)));
            this.accountRepository.save(account);
            this.operationRepository.save(operation);
            account.addOperation(operation);
        }
    }

}
