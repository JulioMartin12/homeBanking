package com.mindhub.homebanking.dtos;

public class LoanApplicationDTO {
    private double amount;
    private int payments;
    private  String toAccountNumber;
    private long loanId;

    public long getLoanId() {
        return loanId;
    }

    public double getAmount() {
        return amount;
    }
    public int getPayments() {
        return payments;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }


}
