package com.ericaud.kata.bankaccount;

import com.ericaud.kata.bankaccount.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {

    public static final String FIRST_NAME = "Dupont";
    public static final String LAST_NAME = "Charlie";

    Account account;

    @BeforeEach
    public void setup() {
        //GIVEN
        account = new Account(1, FIRST_NAME, LAST_NAME);
    }

    @Test
    public void listOperations_onAccount_shouldBeUnmodifiable() {
        assertTrue(account.getOperations().getClass().getSimpleName().equals("UnmodifiableRandomAccessList"));
    }

    @Test
    public void balance_onNewAccount_shouldBeEquals0() {
        //WHEN Account was create
        //THEN Balance is equals to 0
        assertEquals(BigDecimal.ZERO,account.getBalance());
    }

    @Test
    public void operations_onNewAccount_shouldBeEmpty() {
        //WHEN Account was create
        //THEN operations list is empty
        assertTrue(account.getOperations().isEmpty());
    }

    @Test
    public void name_onNewAccount_canBeGet() {
        //WHEN Account was create
        //THEN can get lastname and firstname
        assertEquals(account.getFirstName(), FIRST_NAME);
        assertEquals(account.getLastName(), LAST_NAME);
    }


}
