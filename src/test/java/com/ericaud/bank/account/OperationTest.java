package com.ericaud.bank.account;

import com.ericaud.bank.account.exception.InvalidOperationException;
import com.ericaud.bank.account.model.Operation;
import com.ericaud.bank.account.model.OperationType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationTest {

    @Test
    public void givenNullAmount_whenCreatingAnOperation_thenThrowAnInvalidDepositOperationAmountException() {
        // deposit operation
        assertThrows(InvalidOperationException.class, () -> {
            new Operation(OperationType.DEPOSIT, null);
        });
    }

    @Test
    public void givenNullAmount_whenCreatingAnOperation_thenThrowAnInvalidWithdrawalOperationAmountException() {
        // withdrawal operation
        assertThrows(InvalidOperationException.class, () -> {
            new Operation(OperationType.WITHDRAWAL, null);
        });
    }

}
