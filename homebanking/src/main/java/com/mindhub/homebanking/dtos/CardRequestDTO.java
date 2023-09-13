package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;

public class CardRequestDTO {
    private CardColor cardColor;
    private CardType cardType;

    public CardColor getCardColor() {
        return cardColor;
    }

    public CardType getCardType() {
        return cardType;
    }
}
