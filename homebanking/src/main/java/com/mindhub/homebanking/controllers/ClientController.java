package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.ClientRequestDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.RoleType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.utils.Util;
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
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping(value = "/clients")
    public List<ClientDTO> getClients(){

        return  this.clientService.getAllClients();
    }
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getclient(@PathVariable Long id){
        return this.clientService.getClientById(id) ;
    }
    @GetMapping("/clients/{clientId}/accounts")
    public List<AccountDTO> getClientAccounts(@PathVariable Long clientId) {
        return this.clientService.getClientAccounts(clientId);
    }
    @PostMapping(path = "/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password
           // @RequestBody ClientRequestDTO clientRequestDTO
    ) {
        if (firstName.isEmpty()) {
            return new ResponseEntity<>("Missing data in firstName", HttpStatus.FORBIDDEN);
           //"Missing data", HttpStatus.FORBIDDEN
        }
        if (lastName.isEmpty()) {
            return new ResponseEntity<>("Missing data in lastName", HttpStatus.FORBIDDEN);
            //"Missing data", HttpStatus.FORBIDDEN
        }
        if ( email.isEmpty() ) {
            return new ResponseEntity<>("Missing data in email", HttpStatus.FORBIDDEN);
            //"Missing data", HttpStatus.FORBIDDEN
        }
        if (password.isEmpty()) {
            return new ResponseEntity<>("Missing data in password", HttpStatus.FORBIDDEN);
            //"Missing data", HttpStatus.FORBIDDEN
        }
        if (this.clientService.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }
        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password), RoleType.CLIENT);
        this.clientService.saveClient(client);
        Account account = new Account("VIN-"+ Util.randomNumber(999999999), LocalDate.now(),0);
        client.addAccount(account);
        this.accountService.saveAccount(account);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }
    @GetMapping("/clients/current")
    public ResponseEntity<ClientDTO> getCurrentClient(Authentication authentication) {
        String email = authentication.getName();
        Client client =  this.clientService.findByEmail(email);
        if (client != null) {
            ClientDTO clientDTO = new ClientDTO(client);
            return ResponseEntity.ok(clientDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
