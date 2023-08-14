package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.dtos.ClientLoanDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {



    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private  ClientRepository clientRepository;




    @GetMapping("/clients/{clientId}/clientLoans")
    public Set<ClientLoanDTO> getClientLoans(@PathVariable Long clientId) {
        ResponseEntity<ClientDTO> cliente = clientRepository.findById(clientId)
                .map(ClientDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
       if(cliente != null){
           Set<ClientLoanDTO> loans = cliente.getBody().getLoans();
           return  loans;
       }else{
           throw new EntityNotFoundException("Cliente no encontrado con ID: " + clientId);
       }


    }


    @GetMapping("/loans/{loanId}/clients")
    public Set<ClientLoanDTO> getClientsForLoan(@PathVariable Long loanId) {
        ResponseEntity<LoanDTO> loan = loanRepository.findById(loanId)
                .map(LoanDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        if (loan != null) {
            return loan.getBody().getClients().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
        } else {
            throw new EntityNotFoundException("Loan not found with ID: " + loanId);
        }
    }


}
