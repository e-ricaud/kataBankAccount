package com.ericaud.kata.bankaccount.exception;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException() {
        super("L'opération doit avoir un montant positif.");
    }
}
