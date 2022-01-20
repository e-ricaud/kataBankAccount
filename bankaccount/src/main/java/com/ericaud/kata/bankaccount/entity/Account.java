package com.ericaud.kata.bankaccount.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Account {

    @Id
    @Column(name = "id")
    private final Integer id;
    @Column
    private final String firstName;
    @Column
    private final String lastName;
    @Column
    private BigDecimal balance;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<Operation> operations;

    public Account(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = BigDecimal.ZERO;;
        this.operations = new ArrayList<>();
    }

    public Account() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.balance = BigDecimal.ZERO;
        this.operations = new ArrayList<>();
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public List<Operation> getOperations() {
        return Collections.unmodifiableList(operations);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                ", operations=" + operations +
                '}';
    }
}
