package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.models.RoleType;
import com.mindhub.homebanking.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientRepositoryTest {
    @Autowired
    ClientRepository clientRepository;

    @Test
    public void existClients(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients,is(not(empty())));
    }

    @Test
    public void existPersonalLoan() {
        List<Client> clients = clientRepository.findAll();
        for (Client client :clients) {
           if(client.getRole().compareTo(RoleType.ADMIN)!=0){
               assertThat(client.getAccounts().size(), greaterThan(0));
           }

        }
    }
}