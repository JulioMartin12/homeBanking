package com.mindhub.homebanking.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class AccountRequestDTO {
    private long id;
    private String number;
    private LocalDate date;
    private  double balance;
    private Set<TransactionDTO> transactions ;

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getBalance() {
        return balance;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }
}
