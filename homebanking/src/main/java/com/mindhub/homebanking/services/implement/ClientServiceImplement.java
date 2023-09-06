package com.mindhub.homebanking.services.implement;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
   private ClientRepository clientRepository;

    @Override
    public List<ClientDTO> getAllClients() {
        return  this.clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<ClientDTO> getClientById(Long id) {
        return this.clientRepository.findById(id)
                .map(ClientDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public Client findByEmail(String email) {
        return this.clientRepository.findByEmail(email);
    }

    @Override
    public void saveClient(Client client) {

    }

    @Override
    public List<AccountDTO> getClientAccounts(Long id) {
        return  this.clientRepository.findById(id)
                .map(client -> client.getAccounts().stream()
                        .map(AccountDTO::new)
                        .collect(Collectors.toList()))
                .orElse(null);  // Si no se encuentra el cliente, retorna null
    }

}
