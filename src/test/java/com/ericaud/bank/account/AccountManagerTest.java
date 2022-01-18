package com.ericaud.bank.account;

import com.ericaud.bank.account.exception.InvalidOperationException;
import com.ericaud.bank.account.model.Account;
import com.ericaud.bank.account.model.OperationType;
import com.ericaud.bank.account.service.AccountManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AccountManagerTest {
    AccountManager accountManager;

    @BeforeEach
    public void setup() {
        accountManager = new AccountManager(new Account());
    }

    @Test
    public void givenNewAccount_whenAccountCreated_thenInitialAmountIsZero() {
        assertEquals(BigDecimal.ZERO, accountManager.getAccount().getBalance());
    }

    @Test
    public void givenValidAmount_whenExecutingAnOperation_thenBalanceIsCreditedWithAmount() {
        accountManager.doDeposit(100.);
        assertEquals(100., accountManager.getAccount().getBalance().doubleValue());
    }

    @Test
    public void givenNotPositiveAmount_whenDeposit_thenThrowInvalidOperationAmountException() {
        assertThrows(InvalidOperationException.class, () -> {
            accountManager.doDeposit(-100.);
        });
        assertEquals(0., accountManager.getAccount().getBalance().doubleValue());
    }

    @Test
    public void given0Amount_whenDeposit_thenThrowInvalidOperationAmountException() {
        assertThrows(InvalidOperationException.class, () -> {
            accountManager.doDeposit(0.);
        });

        assertEquals(0., accountManager.getAccount().getBalance().doubleValue());
    }

    @Test
    public void givenValidAmount_whenExecutingAnOperation_thenBalanceIsDebitedWithAmount() {
        accountManager.doWithdraw(100.);
        assertEquals(-100., accountManager.getAccount().getBalance().doubleValue());
    }

    @Test
    public void givenNotPositiveAmount_whenWithdrawal_thenThrowInvalidOperationAmountException() {
        assertThrows(InvalidOperationException.class, () -> {
            accountManager.doWithdraw(-100.);
        });

        assertEquals(0., accountManager.getAccount().getBalance().doubleValue());
    }

    @Test
    public void given0Amount_whenWithdrawal_thenThrowInvalidOperationAmountException() {
        assertThrows(InvalidOperationException.class, () -> {
            accountManager.doWithdraw(0.);
        });

        assertEquals(0., accountManager.getAccount().getBalance().doubleValue());
    }

    @Test
    public void givenValidAmount_whenExecutingAnOperation_thenNewOperationIsAddedToAccount() {
        accountManager.doDeposit(100.);
        accountManager.doWithdraw(10.);
        accountManager.doWithdraw(10.);

        assertEquals(100., accountManager.getAccount().getOperations().get(0).getOperationAmount().doubleValue());
        assertEquals(10., accountManager.getAccount().getOperations().get(1).getOperationAmount().doubleValue());
        assertEquals(OperationType.WITHDRAWAL, accountManager.getAccount().getOperations().get(1).getOperationType());

    }


}
