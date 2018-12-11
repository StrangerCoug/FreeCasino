/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.games.poker;

import freecasino.Player;
import freecasino.enums.CardRank;
import freecasino.objs.Card;
import freecasino.objs.Deck;
import java.math.BigDecimal;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public abstract class Poker {
    protected Player[] players;
    protected BigDecimal betMinimum;
    protected BigDecimal betMaximum;
    protected Deck deck;
    
    /**
     * The ranks in poker. The order of these should not be altered, even though
     * hands do not rank in this order in all games. If a game ranks these hands
     * in a different order, that should be addressed in the game logic of that
     * game.
     */
    protected enum HandRank {
        HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE,
        FOUR_OF_A_KIND, STRAIGHT_FLUSH, WILD_ROYAL_FLUSH, ROYAL_FLUSH,
        FIVE_OF_A_KIND;
    }
    
    private int[] countRanks(Card[] hand) {
        int[] cardsPerRank = new int[14];
        
        for (Card hand1 : hand) {
            cardsPerRank[hand1.getRank().ordinal()]++;
        }
        
        return cardsPerRank;
    }
    
    public boolean isFiveOfAKind(Card[] hand) {
        int[] cardsPerRank = countRanks(hand);
        
        switch (cardsPerRank[CardRank.JOKER.ordinal()]) {
            case 1: return isFourOfAKind(hand);
            case 2: return isThreeOfAKind(hand);
            default: return false;
        }
    }
    
    public boolean isFourOfAKind(Card[] hand) {
        int[] cardsPerRank = countRanks(hand);
        
        for (int i = 0; i < cardsPerRank.length; i++)
            if (cardsPerRank[i] == 4)
                return true;
        
        return false;
    }
    
    public boolean isFlush(Card[] hand) {
        for (int i = 1; i < hand.length; i++)
            if (!hand[i].getSuit().equals(hand[i-1].getSuit()))
                return false;
        
        return true;
    }
    
    public boolean isThreeOfAKind(Card[] hand) {
        int[] cardsPerRank = countRanks(hand);
        
        for (int i = 0; i < cardsPerRank.length; i++)
            if (cardsPerRank[i] == 3)
                return true;
        
        return false;
    }
    
    public boolean isPair(Card[] hand) {
        int[] cardsPerRank = countRanks(hand);
        
        for (int i = 0; i < cardsPerRank.length; i++)
            if (cardsPerRank[i] == 2)
                return true;
        
        return false;
    }
}
