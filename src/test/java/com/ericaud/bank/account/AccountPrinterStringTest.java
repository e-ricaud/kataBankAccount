package com.ericaud.bank.account;

import com.ericaud.bank.account.model.Account;
import com.ericaud.bank.account.service.AccountManager;
import com.ericaud.bank.account.service.AccountPrinter;
import com.ericaud.bank.account.service.AccountPrinterString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AccountPrinterStringTest {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private static Account account;
    @BeforeAll
    public static void setUp() {
        account = new Account();
        AccountManager accountManager = new AccountManager(account);
        accountManager.doDeposit(300.);
        accountManager.doDeposit(150.);
        accountManager.doWithdraw(20.);
    }

    @Test
    public void givenOutputAndAccount_whenCheckingOperations_thenPrintOperationsOnOutput() {

        AccountPrinter printer = new AccountPrinterString(account);
        String result = printer.printOperations();

        String expectedResult = new StringBuilder()
                .append("DATE | OPERATION | AMOUNT")
                .append(System.lineSeparator())
                .append(DATE_FORMATTER.format(LocalDateTime.now())).append(" | DEPOSIT | 300.0")
                .append(System.lineSeparator())
                .append(DATE_FORMATTER.format(LocalDateTime.now())).append(" | DEPOSIT | 150.0")
                .append(System.lineSeparator())
                .append(DATE_FORMATTER.format(LocalDateTime.now())).append(" | WITHDRAWAL | 20.0")
                .append(System.lineSeparator())
                .append("-------------------------")
                .append(System.lineSeparator())
                .append("CURRENT BALANCE : 430.0")
                .append(System.lineSeparator())
                .toString();

        assertEquals(expectedResult, result);

    }

}
