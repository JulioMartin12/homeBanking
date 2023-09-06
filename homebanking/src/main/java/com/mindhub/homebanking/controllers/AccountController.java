package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @GetMapping(value = "/accounts")
    public List<AccountDTO> getAccounts(){
          return  this.accountService.getAllAccounts();
    }
    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDTO> getAccounts(@PathVariable Long id){
        return this.accountService.getAccountById(id);
    }
    @PostMapping(value = "/clients/current/accounts" )
    public ResponseEntity<Object> getCurrentClient(Authentication authentication){
        String email = authentication.getName();
        Client client = clientService.findByEmail(email);
        if(client.getAccounts().size() > 2){
           return new ResponseEntity<>("Max only 3 accounts ", HttpStatus.FORBIDDEN);
        }
        Account account = new Account("VIN"+ Util.randomNumber(999), LocalDate.now(),0);
        client.addAccount(account);
       this.accountService.saveAccount(account);
        return   new ResponseEntity<>("Max only 3 accounts ", HttpStatus.CREATED);
    }
    @GetMapping(value = "/clients/current/accounts" )
    public List<AccountDTO> getCurrentClientAccounts(Authentication authentication) {
        String email = authentication.getName();
       Client clientAuth = clientService.findByEmail(email);
               return  this.clientService.getClientAccounts(clientAuth.getId());
           }
}
