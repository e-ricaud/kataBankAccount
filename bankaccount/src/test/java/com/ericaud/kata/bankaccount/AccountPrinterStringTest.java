package com.ericaud.kata.bankaccount;

import com.ericaud.kata.bankaccount.entity.Account;
import com.ericaud.kata.bankaccount.persistance.AccountRepository;
import com.ericaud.kata.bankaccount.services.AccountManager;
import com.ericaud.kata.bankaccount.services.AccountPrinter;
import com.ericaud.kata.bankaccount.services.AccountPrinterString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountPrinterStringTest {

    public static final Integer ID = 4;
    public static final String FIRST_NAME = "Hash";
    public static final String LAST_NAME = "Tony";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    private Account account;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountManager accountManager;

    @BeforeEach
    public void setUp() {
        //GIVEN
        account = new Account(ID,FIRST_NAME,LAST_NAME);
        this.accountRepository.save(account);

        this.accountManager.doDeposit(300., account);
        this.accountManager.doDeposit(150., account);
        this.accountManager.doWithdraw(20., account);

        this.account = accountRepository.findById(ID).get();
    }

    @Test
    public void printAccount_shouldReturnStringOperations() {

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
