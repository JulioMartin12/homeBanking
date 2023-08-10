package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

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





}
