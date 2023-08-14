package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoanDTO {

    private long id;
    private String name;

    private  double maxAmount;

    private List<Integer> payments = new ArrayList<>();


    private Set<ClientLoan> clients = new HashSet<>();
    public LoanDTO() {
    }

    public LoanDTO(Loan loan) {
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getPayments();
    }

    @JsonIgnore
    public Set<ClientLoan> getClients() {
        return clients;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public double getMaxAmount() {
        return maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }


}
