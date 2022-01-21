package com.ericaud.kata.bankaccount.controller;

import com.ericaud.kata.bankaccount.entity.Account;
import com.ericaud.kata.bankaccount.exception.InvalidOperationException;
import com.ericaud.kata.bankaccount.persistance.AccountRepository;
import com.ericaud.kata.bankaccount.services.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    AccountManager accountManager;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void test() {
        System.out.println("Test Works");
    }

//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Account getAccount(@PathVariable(required = true) Integer id) {
//        Account account = accountRepository.findById(id).get();
//        return account;
//    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Account getAccount(@RequestParam(required = true) Integer id) {
        Account account = accountRepository.findById(id).get();
        return account;
    }

    @PostMapping(value = "/operation/withdraw", produces = MediaType.APPLICATION_JSON_VALUE)
    public void doWithdraw(
            @RequestBody Account account,
            @RequestParam("amount") Double amount
    ) {
        if (amount == 0 || amount < 0 ) {
            throw new InvalidOperationException();
        }
       this.accountManager.doWithdraw(amount, account);
    }

    @PostMapping(value = "/operation/deposit", produces = MediaType.APPLICATION_JSON_VALUE)
    public void doDeposit(
            @RequestBody Account account,
            @RequestParam("amount") Double amount
    ) {
        if (amount == 0 || amount < 0 ) {
            throw new InvalidOperationException();
        }
        this.accountManager.doDeposit(amount, account);
    }
}
