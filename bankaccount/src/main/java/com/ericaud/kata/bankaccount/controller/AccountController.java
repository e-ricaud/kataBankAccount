package com.ericaud.kata.bankaccount.controller;

import com.ericaud.kata.bankaccount.entity.Account;
import com.ericaud.kata.bankaccount.exception.InvalidOperationException;
import com.ericaud.kata.bankaccount.persistance.AccountRepository;
import com.ericaud.kata.bankaccount.services.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable(required = true) Integer id) {
        Account account = accountRepository.findById(id).get();
        return account;
    }

    @PostMapping("/operation/withdraw")
    public void doWithdraw(
            @RequestBody Account account,
            @RequestParam("amount") Double amount
    ) {
        if (amount == 0 || amount < 0 ) {
            throw new InvalidOperationException();
        }
       this.accountManager.doWithdraw(amount, account);
    }

    @PostMapping("/operation/deposit")
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
