package com.ericaud.bank.account;

import com.ericaud.bank.account.model.Account;
import com.ericaud.bank.account.service.AccountManager;
import com.ericaud.bank.account.service.AccountPrinter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AccountPrinterTest {
    private static Account account;

    @BeforeAll
    public static void setUp() {
        account = new Account();
        try {
            AccountManager accountManager = new AccountManager(account);
            accountManager.doDeposit(300.);
            accountManager.doDeposit(150.);
            accountManager.doWithdraw(20.);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void givenOutputAndAccount_whenCheckingOperations_thenPrintOperationsOnOutput() {
        String result = AccountPrinter.printOperationsStr(account);

        String expectedResult = new StringBuilder()
                .append("DATE | OPERATION | AMOUNT")
                .append(System.lineSeparator())
                .append(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(" | DEPOSIT | 300.0")
                .append(System.lineSeparator())
                .append(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(" | DEPOSIT | 150.0")
                .append(System.lineSeparator())
                .append(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append(" | WITHDRAWAL | 20.0")
                .append(System.lineSeparator())
                .append("-------------------------")
                .append(System.lineSeparator())
                .append("CURRENT BALANCE : 430.0")
                .append(System.lineSeparator())
                .toString();

        assertEquals(expectedResult, result);

    }

}
