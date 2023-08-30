package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private ClientRepository  clientRepository;
    @Autowired
    private CardRepository cardRepository;

    @RequestMapping(value = "/clients/current/cards" , method = RequestMethod.POST)
    public ResponseEntity<Object> getCurrentClient(Authentication authentication, @RequestParam CardType cardType, @RequestParam CardColor cardColor) {
        String email = authentication.getName();
        Client client = clientRepository.findByEmail(email);

        if (Utils.ExistTypeCards(client.getCards(),cardColor,cardType)) {
            return new ResponseEntity<>("You have " + cardColor + " " + cardType , HttpStatus.FORBIDDEN);
        }

        String cardNumber = Utils.randomNumber(9999)+ " " + Utils.randomNumber(9999)+ " " + Utils.randomNumber(9999)+ " " + Utils.randomNumber(9999);
        Card card = new Card(client.getFirstName() + " " + client.getLastName(),cardType,cardColor,cardNumber,Utils.randomNumber(999),LocalDate.now() , LocalDate.now().plusYears(5));
        client.addCard(card);
        cardRepository.save(card);
        return new ResponseEntity<>("Card Created ", HttpStatus.CREATED);
    }

    }
