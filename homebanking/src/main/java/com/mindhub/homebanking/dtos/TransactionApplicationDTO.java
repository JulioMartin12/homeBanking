package com.mindhub.homebanking.dtos;

import org.springframework.web.bind.annotation.RequestParam;

public class TransactionApplicationDTO {
    private String fromAccountNumber;
    private String toAccountNumber;
    private Double amount;
    private String description;

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
