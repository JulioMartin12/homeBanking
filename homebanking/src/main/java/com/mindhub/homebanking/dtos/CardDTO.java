package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

public class CardDTO {

      private  long id;

    private  String cardHolder;

    private CardType cardType;

    private CardColor cardColor;

    private String number;

    private int cvv;
    private LocalDate thruDate;
    private  LocalDate fromDate;

    private  boolean activeCard;
    private Client client;

    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardHolder = card.getCardHolder();
        this.cardType = card.getType();
        this.cardColor = card.getColor();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.thruDate = card.getThruDate();
        this.fromDate = card.getFromDate();
        this.activeCard = card.isActiveCard();
    }

    public long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public CardType getType() {
        return cardType;
    }

    public CardColor getColor() {
        return cardColor;
    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public boolean isActiveCard() {
        return activeCard;
    }

    public Client getClient() {
        return client;
    }
}
