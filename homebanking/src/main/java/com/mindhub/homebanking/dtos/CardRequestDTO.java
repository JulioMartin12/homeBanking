package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;

public class CardRequestDTO {
    private CardType type;

    private CardColor color;

    public CardType getType() {
        return type;
    }

    public CardColor getColor() {
        return color;
    }
}
