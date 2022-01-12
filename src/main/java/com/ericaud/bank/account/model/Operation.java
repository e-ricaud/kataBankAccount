package com.ericaud.bank.account.model;

import com.ericaud.bank.account.exception.InvalidOperationException;

import java.math.BigDecimal;
import java.util.Date;

public class Operation {

    private OperationType operationType;
    private BigDecimal operationAmount;

    public Operation(OperationType operationType, BigDecimal operationAmount) throws InvalidOperationException {
        if (operationAmount == null || operationAmount.signum() < 1) {
            throw new InvalidOperationException();
        }
        this.operationType= operationType;
        this.operationAmount= operationAmount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public BigDecimal getOperationAmount() {
        return operationAmount;
    }



}
