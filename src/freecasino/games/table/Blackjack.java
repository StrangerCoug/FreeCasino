/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.games.table;

import freecasino.games.table.TableGame;
import freecasino.Player;
import freecasino.enums.CardRank;
import freecasino.objs.Bet;
import freecasino.objs.Card;
import freecasino.objs.Deck;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public class Blackjack implements TableGame {
    private Player[] players;
    private BigDecimal betMinimum;
    private BigDecimal betMaximum;
    private Deck deck;
    private LinkedList<LinkedList<Card>> playerHands;
    private LinkedList<Card> dealerHand;
    private HashSet<Bet> bets;
            
    @Override
    public void play(Player[] players, BigDecimal betMinimum,
            BigDecimal betMaximum) {
        this.players = players;
        this.betMinimum = betMinimum;
        this.betMaximum = betMaximum;
        
        playerHands = new LinkedList<>();
        dealerHand = new LinkedList<>();
        
        for (Player player : players) {
            playerHands.add(new LinkedList<>());
        }
    }
    
    /**
     * 
     * 
     * @param card the card whose value is being checked
     * @return the card's value
     */
    private int getCardValue(Card card) {
        switch (card.getRank()) {
            case ACE: return 1;
            case TWO: return 2;
            case THREE: return 3;
            case FOUR: return 4;
            case FIVE: return 5;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            default: return 10;
        }
    }
    
    /* TODO: Determine if there is a more efficient way to calculate a hand's
     * value and whether it's hard or soft than the next three methods.
     */
    
    /**
     * Determines the value of a hand for the purposes of the game, correctly
     * treating an ace as 11 points when a hand is soft.
     * 
     * @param hand the hand whose value is being checked
     * @return the hand's value
     */
    private int getHandValue(LinkedList<Card> hand) {
        int total = getHardHandValue(hand);
        
        if (total <= 11) {
            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).getRank() == CardRank.ACE) {
                    total += 10;
                    break;
                }
            }
        }
        
        return total;
    }
    
    /**
     * Determines the value of a hand as if it were hard, i.e. always treating
     * an ace as 1 point.
     * 
     * @param hand the hand whose value is being checked
     * @return the hand's value
     */
    private int getHardHandValue(LinkedList<Card> hand) {
        int total = 0;
        
        for (int i = 0; i < hand.size(); i++)
            total += getCardValue(hand.get(i));
        
        return total;
    }
    
    /**
     * Determines whether the hand is a soft hand, i.e. has an ace that can
     * safely be counted as 11 points instead of 1 point.
     * 
     * @param hand the hand being checked
     * @return true if the hand is soft, false otherwise
     */
    private boolean isHandSoft(LinkedList<Card> hand) {
        return getHandValue(hand) == getHardHandValue(hand) + 10;
    }
}
