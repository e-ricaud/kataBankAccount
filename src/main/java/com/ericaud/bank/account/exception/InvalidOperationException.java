package com.ericaud.bank.account.exception;

public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException() {
        super("L'opération doit avoir un montant positif.");
    }


}
