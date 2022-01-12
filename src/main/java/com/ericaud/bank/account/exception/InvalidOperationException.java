package com.ericaud.bank.account.exception;

public class InvalidOperationException extends Exception {

    public InvalidOperationException() {
        super("L'opération doit avoir un montant positif.");
    }


}
