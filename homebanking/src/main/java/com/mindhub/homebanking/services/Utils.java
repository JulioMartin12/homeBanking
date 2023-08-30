package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;

import java.util.Random;
import java.util.Set;

public final class Utils {
    public static int randomNumber(int numberDigits){
             return new Random().nextInt(numberDigits);
    }

    public static boolean ExistTypeCards(Set<Card> cards, CardColor cardColor, CardType cardType){

        for (Card card:cards
             ) {
            if(cardType.equals(card.getType()) && cardColor.equals(card.getColor())){
                return true;
            }
        }

        return false ;
    }

}