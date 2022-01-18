package com.ericaud.bank.account.service;

import com.ericaud.bank.account.model.Account;

import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountPrinterString implements AccountPrinter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private final Account account;
    public AccountPrinterString(Account account) {
        this.account = account;
    }

    public String printOperations() {
        StringBuilder stringBuilder = new StringBuilder()
                .append("DATE | OPERATION | AMOUNT")
                .append(System.lineSeparator());

        this.account.getOperations().forEach(operation -> {
            stringBuilder.append(DATE_FORMATTER.format(operation.getDateOperation())).append(" | ")
                    .append(operation.getOperationType()).append(" | ")
                    .append(operation.getOperationAmount().setScale(2, RoundingMode.HALF_EVEN).doubleValue())
                    .append(System.lineSeparator());
        });

        stringBuilder.append("-------------------------")
                .append(System.lineSeparator())
                .append("CURRENT BALANCE : ")
                .append(this.account.getBalance().setScale(2, RoundingMode.HALF_EVEN).doubleValue())
                .append(System.lineSeparator());

        return stringBuilder.toString();
    }

}
