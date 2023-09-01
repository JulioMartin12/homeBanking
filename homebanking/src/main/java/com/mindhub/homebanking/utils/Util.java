package com.mindhub.homebanking.utils;

import com.mindhub.homebanking.models.*;

import java.util.Random;
import java.util.Set;

public final class Util {
    public static int randomNumber(int numberDigits){
             return new Random().nextInt(numberDigits);
    }

    public static boolean existTypeCards(Set<Card> cards, CardColor cardColor, CardType cardType){

        for (Card card:cards
             ) {
            if(cardType.equals(card.getType()) && cardColor.equals(card.getColor())){
                return true;
            }
        }

        return false ;
    }

       public static boolean availableBalance(Set<Account> accounts, String originNumber, Double amount){

        for (Account account: accounts
        ) {
            if(account.getNumber().equals(originNumber) && account.getBalance() >= amount){

                return true;
            }
        }

        return false ;
    }

}