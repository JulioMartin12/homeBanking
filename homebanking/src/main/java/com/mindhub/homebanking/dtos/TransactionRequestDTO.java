package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionRequestDTO {
    private  Long id;
    private TransactionType type;
    private String description;
    private  double amount;
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
