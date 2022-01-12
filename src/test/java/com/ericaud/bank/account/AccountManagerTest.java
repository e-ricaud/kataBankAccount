package com.ericaud.bank.account;

import com.ericaud.bank.account.exception.InvalidOperationException;
import com.ericaud.bank.account.model.Account;
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
        try {
            accountManager.doDeposit(100.);
        } catch(Exception e) {
            fail(e.getMessage());
        }

        assertEquals(100., accountManager.getAccount().getBalance().doubleValue());
    }

    @Test
    public void givenNotPositiveOrNullAmount_whenDeposit_thenThrowInvalidOperationAmountException() {
        assertThrows(InvalidOperationException.class, () -> {
            accountManager.doDeposit(-100.);
        });
        assertThrows(InvalidOperationException.class, () -> {
            accountManager.doDeposit(0.);
        });

        assertEquals(0., accountManager.getAccount().getBalance().doubleValue());
    }
}
