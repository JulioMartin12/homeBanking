package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;

public class CardRequestDTO {
    private CardType cardType;

    private CardColor cardColor;

    public CardRequestDTO() {
    }

    public CardType getType() {
        return cardType;
    }

    public CardColor getColor() {
        return cardColor;
    }
}
