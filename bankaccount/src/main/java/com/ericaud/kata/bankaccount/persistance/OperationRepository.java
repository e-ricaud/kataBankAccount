package com.ericaud.kata.bankaccount.persistance;

import com.ericaud.kata.bankaccount.entity.Account;
import com.ericaud.kata.bankaccount.entity.Operation;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<Operation, Integer> {}
