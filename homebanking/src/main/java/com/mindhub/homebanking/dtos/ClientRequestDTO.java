package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.RoleType;

import java.util.Set;

public class ClientRequestDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private RoleType role;
    private String password;
    private Set<AccountDTO> accounts;
    private  Set<ClientLoanDTO> loans ;
    private  Set<CardDTO> cards;
    public long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public RoleType getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }
    public Set<ClientLoanDTO> getLoans() {
        return loans;
    }
    public Set<CardDTO> getCards() {
        return cards;
    }
}
