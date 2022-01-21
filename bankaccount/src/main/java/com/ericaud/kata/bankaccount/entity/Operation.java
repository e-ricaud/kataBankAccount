package com.ericaud.kata.bankaccount.entity;


import com.ericaud.kata.bankaccount.exception.InvalidOperationException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public final class Operation {

    @Id
    @Column(name="idOp")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idOp;
    @Column
    private final OperationType operationType;
    @Column
    private final BigDecimal operationAmount;
    @Column
    private final LocalDateTime dateOperation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    public Operation() {
        this.operationType= null;
        this.operationAmount= null;
        this.dateOperation = LocalDateTime.now();
        this.account = null;
    }

    public Operation(OperationType operationType, BigDecimal operationAmount, Account account) throws InvalidOperationException {
        if (operationAmount == null || operationAmount.signum() < 1) {
            throw new InvalidOperationException();
        }
        this.operationType= operationType;
        this.operationAmount= operationAmount;
        this.dateOperation = LocalDateTime.now();
        this.account = account;
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

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + idOp +
                ", operationType=" + operationType +
                ", operationAmount=" + operationAmount +
                ", dateOperation=" + dateOperation +
                '}';
    }
}
