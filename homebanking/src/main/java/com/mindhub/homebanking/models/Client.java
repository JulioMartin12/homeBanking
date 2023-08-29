package com.mindhub.homebanking.models;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private  RoleType role;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private  Set<ClientLoan> loans = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private  Set<Card> cards = new HashSet<>();


    public Client() {
    }

    public Client(String firstName, String lastName, String email, String password, RoleType role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password=password;
        this.role=role;
    }


    public Set<Loan> getClientLoans() {
        return loans.stream().map(ClientLoan::getLoan).collect(Collectors.toSet());
    }
    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }


    public Set<ClientLoan> getLoans() {
        return loans;
    }

    public void setClientLoans(Set<ClientLoan> loans) {
        this.loans = loans;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addAccount(Account account){
        this.accounts.add(account);
        account.setClient(this);
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void setLoans(Set<ClientLoan> loans) {
        this.loans = loans;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addClientLoan(ClientLoan clientLoan){
        this.loans.add(clientLoan);
        clientLoan.setClient(this);
    }

    public  void addCard(Card card){
       this.cards.add(card);
       card.setClient(this);

    }


}
