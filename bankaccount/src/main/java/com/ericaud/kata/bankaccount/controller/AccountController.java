package com.ericaud.kata.bankaccount.controller;

import com.ericaud.kata.bankaccount.controller.model.JSONResponse;
import com.ericaud.kata.bankaccount.controller.model.OperationForm;
import com.ericaud.kata.bankaccount.entity.Account;
import com.ericaud.kata.bankaccount.exception.InvalidOperationException;
import com.ericaud.kata.bankaccount.persistance.AccountRepository;
import com.ericaud.kata.bankaccount.services.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    AccountManager accountManager;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Account getAccount(@RequestParam(required = true) Integer id) {
        Account account = accountRepository.findById(id).get();
        return account;
    }

    @GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Account> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    @PostMapping(value = "/operation/withdraw", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONResponse doWithdraw(@RequestBody OperationForm operationForm) {
        if (operationForm.getAmount() == 0 || operationForm.getAmount() < 0 ) {
            throw new InvalidOperationException();
        }
        this.accountManager.doWithdraw(operationForm.getAmount(), operationForm.getAccount());
        return new JSONResponse("ok");
    }

    @PostMapping(value = "/operation/deposit", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONResponse doDeposit(@RequestBody OperationForm operationForm) {
        if (operationForm.getAmount() == 0 || operationForm.getAmount() < 0 ) {
            throw new InvalidOperationException();
        }
        this.accountManager.doDeposit(operationForm.getAmount(), operationForm.getAccount());
        return new JSONResponse("ok");
    }
}
