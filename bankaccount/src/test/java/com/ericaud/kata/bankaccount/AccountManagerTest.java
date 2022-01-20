package com.ericaud.kata.bankaccount;

import com.ericaud.kata.bankaccount.entity.Account;
import com.ericaud.kata.bankaccount.entity.OperationType;
import com.ericaud.kata.bankaccount.exception.InvalidOperationException;
import com.ericaud.kata.bankaccount.persistance.AccountRepository;
import com.ericaud.kata.bankaccount.services.AccountManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountManagerTest {

    public static final Integer ID = 3;
    public static final String FIRST_NAME = "Martin";
    public static final String LAST_NAME = "Peneloppe";

    @Autowired
    AccountManager accountManager;

    @Autowired
    AccountRepository accountRepository;

    private Account account;

    @BeforeEach
    public void setup() {
        //GIVEN
        account = new Account(ID,FIRST_NAME,LAST_NAME);
        accountRepository.save(account);
    }

    @AfterEach
    public void deleteAccount() {
        accountRepository.deleteAll();
    }

    @Test
    public void depositValidAmount_shouldCreditedAccount() {
        //WHEN deposit with valid amount
        accountManager.doDeposit(100.,this.account);
        //THEN balance is credited and add operation in account
        account = accountRepository.findById(ID).get();

        assertEquals(100., this.account.getBalance().doubleValue());
        assertTrue(this.account.getOperations().size() == 1);
    }

    @Test
    public void notPositiveAmount_shouldThrowInvalidOperationAmountException() {
        //WHEN deposit a neg amount
        //THEN throw exception and not update the balance
        assertThrows(InvalidOperationException.class, () -> {
            accountManager.doDeposit(-100., account);
        });
        account = accountRepository.findById(ID).get();
        assertEquals(0., this.account.getBalance().doubleValue());
    }

    @Test
    public void deposit0Amount_shouldThrowInvalidOperationAmountException() {
        //WHEN deposit 0
        //THEN throw exception and not update the balance
        assertThrows(InvalidOperationException.class, () -> {
            accountManager.doDeposit(0., account);
        });

        account = accountRepository.findById(ID).get();
        assertEquals(0., this.account.getBalance().doubleValue());
    }

    @Test
    public void withdrawValidAmount_balanceIsDebitedWithAmount() {
        //WHEN withdraw 100
        //THEN account was debited
        accountManager.doWithdraw(100.,account);
        account = accountRepository.findById(ID).get();
        assertEquals(-100., this.account.getBalance().doubleValue());
    }



    @Test
    public void validDepositAndWithdraw_shouldAddNewOperations() {
        //WHEN doing multiple deposit and withdraw
        accountManager.doDeposit(100., this.account);
        accountManager.doWithdraw(10., this.account);
        accountManager.doWithdraw(10., this.account);

        Account accountRead = accountRepository.findById(ID).get();

        //THEN Operations is added to the account
        assertEquals(100., accountRead.getOperations().get(0).getOperationAmount().doubleValue());
        assertEquals(10., accountRead.getOperations().get(1).getOperationAmount().doubleValue());
        assertEquals(OperationType.WITHDRAWAL, accountRead.getOperations().get(1).getOperationType());

    }

}
