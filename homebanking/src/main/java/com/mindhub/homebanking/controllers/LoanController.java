package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private ClientLoanService clientLoanService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @GetMapping("/loans")
    public List<LoanDTO> getLoans() {
        List<LoanDTO> loans;
        return this.loanService.getLoans();
    }
    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> getClientLoans(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication) {
        String email = authentication.getName();
        Client client = this.clientService.findByEmail(email);

        if (client == null) {
            return ResponseEntity.notFound().build();
        }
       //Realizar control de Blancos
         if(loanApplicationDTO.getAmount()<= 0){
            return new ResponseEntity<>("Must enter an amount greater than zero",HttpStatus.FORBIDDEN);
        }

        if(loanApplicationDTO.getPayments()<= 0){
            return new ResponseEntity<>("Must enter an payment greater than zero",HttpStatus.FORBIDDEN);
        }

        if(loanApplicationDTO.getToAccountNumber().isEmpty()){
            return new ResponseEntity<>("Destination account is empty.",HttpStatus.FORBIDDEN);
        }

        Account account = this.accountService.findByNumber(loanApplicationDTO.getToAccountNumber());
        if(account == null){
            return new ResponseEntity<>("Not Exist account number",HttpStatus.FORBIDDEN);
        }


        if(account.getClient().getId() != client.getId()){
            return new ResponseEntity<>("The account number is not yours",HttpStatus.FORBIDDEN);
        }
        Loan loan = this.loanService.findById(loanApplicationDTO.getLoanId());

        if(loanApplicationDTO.getAmount() > loan.getMaxAmount()){
            return new ResponseEntity<>("amount exceeds the maximum allowed",HttpStatus.FORBIDDEN);
        }
        if( loan.getId() != loanApplicationDTO.getLoanId()){
            return new ResponseEntity<>("Not Exist loan type",HttpStatus.FORBIDDEN);
        }
        Transaction transaction = new Transaction(TransactionType.CREDIT,loan.getName() + " loan approved ",loanApplicationDTO.getAmount() +(loanApplicationDTO.getAmount()*0.2), LocalDateTime.now());
        account.setBalance(account.getBalance()+ loanApplicationDTO.getAmount());
        account.addTransaction(transaction);
       this.transactionService.saveTransaction(transaction);

       ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount(), loanApplicationDTO.getPayments());
      loan.addClientLoans(clientLoan);
      client.addClientLoan(clientLoan);
       this.clientLoanService.saveClientLoan(clientLoan);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
}
