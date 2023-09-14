package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.dtos.CardRequestDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.services.CardService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private CardService cardService;
    @PostMapping(value = "/clients/current/cards")
    public ResponseEntity<Object> getCurrentClient(@RequestBody CardRequestDTO cardRequestDTO, Authentication authentication) {
        String email = authentication.getName();
        Client client = this.clientService.findByEmail(email);
        if (Util.existTypeCards(client.getCards(),cardRequestDTO.getCardColor(), cardRequestDTO.getCardType())) {
            return new ResponseEntity<>("You have " +cardRequestDTO.getCardColor() + " " +cardRequestDTO.getCardType() , HttpStatus.FORBIDDEN);
        }
        String cardNumber = Util.randomNumber(9999)+ " " + Util.randomNumber(9999)+ " " + Util.randomNumber(9999)+ " " + Util.randomNumber(9999);
        Card card = new Card(client.getFirstName() + " " + client.getLastName(),cardRequestDTO.getCardType(),cardRequestDTO.getCardColor(),cardNumber, Util.randomNumber(999),LocalDate.now().plusYears(10) , LocalDate.now(),true);
        client.addCard(card);
       this.cardService.saveCard(card);
        return new ResponseEntity<>("Card Created ", HttpStatus.CREATED);
    }

    @GetMapping(value = "/clients/current/cards")
    public List<CardDTO> getCurrentClientCards(Authentication authentication) {
        String email = authentication.getName();
        Client clientAuth = clientService.findByEmail(email);
        return  this.clientService.getClientCards(clientAuth.getId());
    }
    @PatchMapping(value = "/clients/current/cards")
    public ResponseEntity<Object> getCurrentClientDeleteCard(@RequestParam String number, Authentication authentication) {
        String email = authentication.getName();
        Client client = this.clientService.findByEmail(email);
        if (client != null) {
            Card card = cardService.getCard(number);
            if (card != null) {
                card.setActiveCard(false);
                cardService.saveCard(card);
                return new ResponseEntity<>("Card delete ", HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Error Not exist card number ", HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>("user Not autheticated ", HttpStatus.FORBIDDEN);
        }
      }
    }
