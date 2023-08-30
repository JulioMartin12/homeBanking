package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.Utils;
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
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/accounts")
    public List<AccountDTO> getAccounts(){
        List<AccountDTO> accounts;
        accounts = this.accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
        return  accounts;


    }

    @RequestMapping("/accounts/{id}")
    public ResponseEntity<AccountDTO> getAccounts(@PathVariable Long id){
        return accountRepository.findById(id)
                .map(AccountDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/clients/current/accounts" , method = RequestMethod.POST)
    public ResponseEntity<Object> getCurrentClient(Authentication authentication){
        String email = authentication.getName();
        Client client = clientRepository.findByEmail(email);

        if(client.getAccounts().size() > 2){
           return new ResponseEntity<>("Max only 3 accounts ", HttpStatus.FORBIDDEN);
        }
        Account account = new Account("VIN-"+ Utils.randomNumber(99999999), LocalDate.now(),0);
        client.addAccount(account);
        accountRepository.save(account);
        return   new ResponseEntity<>("Max only 3 accounts ", HttpStatus.CREATED);

    }

}
