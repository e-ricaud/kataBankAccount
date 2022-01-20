package com.ericaud.kata.bankaccount.persistance;

import com.ericaud.kata.bankaccount.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {}
