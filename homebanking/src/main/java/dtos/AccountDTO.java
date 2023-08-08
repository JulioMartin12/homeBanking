package dtos;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;

import java.time.LocalDate;

public class AccountDTO {

    private long id;
    private String number;
    private LocalDate creationData;
    private  double balance;
    private Client client;
    public AccountDTO(Account account) {
        this.id =account.getId();
        this.number=account.getNumber();
        this.balance= account.getBalance();
        this.creationData = account.getCreationData();
        this.client=account.getClient();

    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationData() {
        return creationData;
    }

    public double getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }
}
