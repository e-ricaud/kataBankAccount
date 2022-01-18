package com.ericaud.bank.account;

import com.ericaud.bank.account.exception.InvalidOperationException;
import com.ericaud.bank.account.model.Account;
import com.ericaud.bank.account.model.Operation;
import com.ericaud.bank.account.model.OperationType;
import com.ericaud.bank.account.service.AccountManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    AccountManager accountManager;

    @BeforeEach
    public void setup() {
        accountManager = new AccountManager(new Account());
    }

    @Test
    public void givenValidAccount_whenGetOperations_thenItsAUnmodifiableCollection() {
        assertTrue(accountManager.getAccount().getOperations().getClass().getSimpleName().equals("UnmodifiableRandomAccessList"));
    }
}
