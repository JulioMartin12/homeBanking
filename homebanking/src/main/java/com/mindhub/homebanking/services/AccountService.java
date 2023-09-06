package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {

    List<AccountDTO>getAllAccounts();
    ResponseEntity<AccountDTO>getAccountById(Long id);

    void saveAccount(Account account);

   Account findByNumber(String number);

}
