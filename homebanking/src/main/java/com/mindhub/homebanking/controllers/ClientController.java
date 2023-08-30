package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.RoleType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.services.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/clients" , method = RequestMethod.GET)
    public List<ClientDTO> getClients(){
  List<ClientDTO> clients;
  clients = this.clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
  return  clients;


    }

    @RequestMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getclient(@PathVariable Long id){
        return  clientRepository.findById(id)
                .map(ClientDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());



    }

    @GetMapping("/clients/{clientId}/accounts")
    public List<AccountDTO> getClientAccounts(@PathVariable Long clientId) {
        return clientRepository.findById(clientId)
                .map(client -> client.getAccounts().stream()
                        .map(account -> new AccountDTO(account))
                        .collect(Collectors.toList()))
                .orElse(null);  // Si no se encuentra el cliente, retorna null
    }

    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty()) {

            return new ResponseEntity<>("Missing data in firstName", HttpStatus.FORBIDDEN);
            //"Missing data", HttpStatus.FORBIDDEN
        }
        if ( lastName.isEmpty()) {

            return new ResponseEntity<>("Missing data in lastName", HttpStatus.FORBIDDEN);
            //"Missing data", HttpStatus.FORBIDDEN
        }
        if ( email.isEmpty() ) {

            return new ResponseEntity<>("Missing data in email", HttpStatus.FORBIDDEN);
            //"Missing data", HttpStatus.FORBIDDEN
        }
        if ( password.isEmpty()) {

            return new ResponseEntity<>("Missing data in password", HttpStatus.FORBIDDEN);
            //"Missing data", HttpStatus.FORBIDDEN
        }

        if (clientRepository.findByEmail(email) !=  null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password), RoleType.CLIENT);
        clientRepository.save( client);
        Account account = new Account("VIN-"+ Utils.randomNumber(999999999), LocalDate.now(),0);
        client.addAccount(account);
        accountRepository.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/clients/current")
    public ResponseEntity<ClientDTO> getCurrentClient(Authentication authentication) {
        String email = authentication.getName();
        Client client = clientRepository.findByEmail(email);

        if (client != null) {
            ClientDTO clientDTO = new ClientDTO(client);
            return ResponseEntity.ok(clientDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
