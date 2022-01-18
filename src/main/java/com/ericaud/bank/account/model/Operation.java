package com.ericaud.bank.account.model;

import com.ericaud.bank.account.exception.InvalidOperationException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class Operation {

    private final OperationType operationType;
    private final BigDecimal operationAmount;
    private final LocalDateTime dateOperation;

    public Operation(OperationType operationType, BigDecimal operationAmount) throws InvalidOperationException {
        if (operationAmount == null || operationAmount.signum() < 1) {
            throw new InvalidOperationException();
        }
        this.operationType= operationType;
        this.operationAmount= operationAmount;
        this.dateOperation = LocalDateTime.now();
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public BigDecimal getOperationAmount() {
        return operationAmount;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }
}
