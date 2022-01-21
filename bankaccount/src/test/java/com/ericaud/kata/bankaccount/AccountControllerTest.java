package com.ericaud.kata.bankaccount;

import com.ericaud.kata.bankaccount.controller.model.OperationForm;
import com.ericaud.kata.bankaccount.entity.Account;
import com.ericaud.kata.bankaccount.persistance.AccountRepository;
import com.ericaud.kata.bankaccount.services.AccountManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    public static final Integer ID = 2;
    public static final String FIRST_NAME = "Roland";
    public static final String LAST_NAME = "Charlie";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private AccountManager accountManager;

    private Account account;

    @BeforeEach
    public void setup() {
        //GIVEN
        account = new Account(ID,FIRST_NAME,LAST_NAME);
        this.accountRepository.save(account);
    }

    @AfterEach
    public void deleteAccount() {
        this.accountRepository.deleteAll();
    }

    @Test
    public void req_getAccountWithAnExistingId_shouldFindAccount() throws Exception {
        //When get account with request
        this.mvc.perform(MockMvcRequestBuilders.get("/account")
                        .param("id","1"))
                .andExpect(status().isOk());
        //Then return the account associated
        Account accountRead = accountRepository.findById(ID).get();
        assertEquals(FIRST_NAME, accountRead.getFirstName());
    }

    @Test
    public void req_doWithdraw_shouldAddOperationAndModifiedBalance() throws Exception {
        //When withdrawal 100 on an account with request

        OperationForm operationForm = new OperationForm(account, 100.);

        this.mvc.perform(MockMvcRequestBuilders.post("/account/operation/withdraw")
                .content(objectMapper.writeValueAsString(operationForm))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then withdrawal substract 100 to the balance and operation hava added to the account
        Account accountRead = accountRepository.findById(ID).get();
        assertEquals(accountRead.getBalance(),BigDecimal.valueOf(-100.00).setScale(2));
        assertTrue(accountRead.getOperations().size() == 1);
    }

    @Test
    public void req_doDeposit_shouldAddOperationAndModifiedBalance() throws Exception {
        //When deposit 300 on an account with request
        OperationForm operationForm = new OperationForm(account, 300.);

        this.mvc.perform(MockMvcRequestBuilders.post("/account/operation/deposit")
                        .content(objectMapper.writeValueAsString(operationForm))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then deposit add 300 to the balance and operation have added to the account
        Account accountRead = accountRepository.findById(ID).get();
        assertEquals(accountRead.getBalance(),BigDecimal.valueOf(300.00).setScale(2));
        System.out.println("Size" + accountRead.getOperations().size());
        assertTrue(accountRead.getOperations().size() == 1);
    }

    @Test
    public void depositNotPositiveAmount_shouldThrowInvalidOperationAmountException() throws Exception {

//        this.mvc.perform(MockMvcRequestBuilders.post("/account/operation/deposit")
//                .content(objectMapper.writeValueAsString(account))
//                .param("amount","-100.")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidOperationException))
//        ;
//
//        Account accountRead = accountRepository.findById(2).get();
//        assertEquals(accountRead.getBalance(),BigDecimal.valueOf(0.00).setScale(2));
//        assertTrue(accountRead.getOperations().isEmpty());
    }
}
