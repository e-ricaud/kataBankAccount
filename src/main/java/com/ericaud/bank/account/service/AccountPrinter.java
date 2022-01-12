package com.ericaud.bank.account.service;

import com.ericaud.bank.account.model.Account;

import java.math.RoundingMode;
import java.text.SimpleDateFormat;

public class AccountPrinter {

    public static String printOperationsStr(final Account account) {
        StringBuilder stringBuilder = new StringBuilder()
                .append("DATE | OPERATION | AMOUNT")
                .append(System.lineSeparator());

        account.getOperations().forEach(operation -> {
            stringBuilder.append(new SimpleDateFormat("yyyy-MM-dd").format(operation.getDateOperation())).append(" | ")
                    .append(operation.getOperationType()).append(" | ")
                    .append(operation.getOperationAmount().setScale(2, RoundingMode.HALF_EVEN).doubleValue())
                    .append(System.lineSeparator());
        });

        stringBuilder.append("-------------------------")
                .append(System.lineSeparator())
                .append("CURRENT BALANCE : ")
                .append(account.getBalance().setScale(2, RoundingMode.HALF_EVEN).doubleValue())
                .append(System.lineSeparator());

        return stringBuilder.toString();
    }

}
