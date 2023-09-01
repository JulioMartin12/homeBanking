package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
   private TransactionRepository transactionRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;





    @Transactional
    @PostMapping(value ="/transactions" )
    public ResponseEntity<Object> getCurrentClientTransaction(
            Authentication authentication, @RequestParam String fromAccountNumber ,
            @RequestParam  String toAccountNumber, @RequestParam Double amount,
            @RequestParam String description
    ){
        String email = authentication.getName();
        Client client = clientRepository.findByEmail(email);
        if (amount.isNaN()) {

            return new ResponseEntity<>("Missing data in amount", HttpStatus.FORBIDDEN);

        }

        if (amount < 0) {

            return new ResponseEntity<>("Negative number", HttpStatus.FORBIDDEN);

        }

        if ( description.isEmpty()) {

            return new ResponseEntity<>("Missing data in description", HttpStatus.FORBIDDEN);

        }
        if ( fromAccountNumber.isEmpty() ) {

            return new ResponseEntity<>("Missing data in originNumber", HttpStatus.FORBIDDEN);

        }
        if ( toAccountNumber.isEmpty()) {

            return new ResponseEntity<>("Missing data in destinationNumber", HttpStatus.FORBIDDEN);

        }
        if(fromAccountNumber.equals(toAccountNumber)){
            return new ResponseEntity<>("Equals Accounts ", HttpStatus.FORBIDDEN);
        }

        Account originAccount = accountRepository.findByNumber(fromAccountNumber);
        if(originAccount == null){
            return new ResponseEntity<>("Not exist origin account", HttpStatus.FORBIDDEN);
        }

        Account destinationAccount = accountRepository.findByNumber(toAccountNumber);
        if(destinationAccount == null){
            return new ResponseEntity<>("Not exist destination account", HttpStatus.FORBIDDEN);
        }

        if(!Util.availableBalance(client.getAccounts(),originAccount.getNumber(),amount)){
            return new ResponseEntity<>("insufficient balance in your account", HttpStatus.FORBIDDEN);
        }

       /* Account accountOrigin=Utils.updateBalance(client.getAccounts(),toAccountNumber,amount, TransactionType.DEBIT);
        if(accountOrigin == null){
            return new ResponseEntity<>("Error in transaction origin account" , HttpStatus.FORBIDDEN);
        }

         Account accountDestination =Utils.updateBalance(client.getAccounts(),toAccountNumber,amount, TransactionType.CREDIT);
        if(accountDestination== null){
            return new ResponseEntity<>("Error in transaction destination account", HttpStatus.FORBIDDEN);
        }
*/

        Transaction transactionDebit= new Transaction(TransactionType.DEBIT,description,amount*-1, LocalDateTime.now());
        originAccount.setBalance(originAccount.getBalance() - amount);
        originAccount.addTransaction(transactionDebit);
        transactionRepository.save(transactionDebit);
        accountRepository.save(originAccount);

        Transaction transactionCredit= new Transaction(TransactionType.CREDIT,description,amount, LocalDateTime.now());
        destinationAccount.setBalance(destinationAccount.getBalance()+ amount);
        destinationAccount.addTransaction(transactionCredit);
        transactionRepository.save(transactionCredit);
        accountRepository.save(destinationAccount);
         return   new ResponseEntity<>("Ok", HttpStatus.CREATED);

    }

}
