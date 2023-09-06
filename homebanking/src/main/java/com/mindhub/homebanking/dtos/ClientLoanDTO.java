package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;


public class ClientLoanDTO {

    private long Id;
    private double Amount;
    private int payments;
    private  String name;

    private long clientId;

    private long loanId;




    public ClientLoanDTO(ClientLoan clientLoan) {
        this.Id = clientLoan.getId();
        this.Amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
        this.clientId = clientLoan.getClient().getId();
        this.loanId = clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();
    }


    public long getId() {
        return Id;
    }

    public long getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public long getLoanId() {
        return loanId;
    }

    public double getAmount() {
        return Amount;
    }

     public int getPayments() {
        return payments;
    }






}
