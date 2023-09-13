package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();
    ResponseEntity<ClientDTO> getClientById(Long id);
    Client findByEmail(String email);
    void saveClient(Client client);

    List<AccountDTO> getClientAccounts(Long id);
    List<CardDTO> getClientCards(Long id);

}
