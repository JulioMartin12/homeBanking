package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.CardRequestDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.CardService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private CardService cardService;
    @PostMapping(value = "/clients/current/cards")
    public ResponseEntity<Object> getCurrentClient(Authentication authentication,
                                               /*  @RequestParam CardType cardType ,
                                                 @RequestParam CardColor cardColor*/
                                                 @RequestBody CardRequestDTO cardRequestDTO) {
        String email = authentication.getName();
        Client client = this.clientService.findByEmail(email);
        if (Util.existTypeCards(client.getCards(),cardRequestDTO.getColor(), cardRequestDTO.getType())) {
            return new ResponseEntity<>("You have " +cardRequestDTO.getColor() + " " +cardRequestDTO.getType() , HttpStatus.FORBIDDEN);
        }
        String cardNumber = Util.randomNumber(9999)+ " " + Util.randomNumber(9999)+ " " + Util.randomNumber(9999)+ " " + Util.randomNumber(9999);
        Card card = new Card(client.getFirstName() + " " + client.getLastName(),cardRequestDTO.getType(),cardRequestDTO.getColor(),cardNumber, Util.randomNumber(999),LocalDate.now() , LocalDate.now().plusYears(5));
        client.addCard(card);
       this.cardService.saveCard(card);
        return new ResponseEntity<>("Card Created ", HttpStatus.CREATED);
    }

    }
