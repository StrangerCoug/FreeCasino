/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.games.table;

import freecasino.Player;
import freecasino.objs.Bet;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import freecasino.games.table.TableGame;
import freecasino.enums.Action;
import freecasino.objs.Card;
import freecasino.objs.Deck;

/**
 * The class for a baccarat (more specifically, punto banco) game. The game
 * logic is designed not to care about at how big of a table the game is played;
 * therefore, it considers standard baccarat, midi baccarat, and mini baccarat
 * the same game. Which is which is to be determined by checking the length of
 * the player array passed to it (usually 14 but sometimes 12 for the standard
 * game, 9 for midi, and 7 for mini).
 * 
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public class Baccarat implements TableGame {
    private Player[] players;
    private BigDecimal betMinimum;
    private BigDecimal betMaximum;
    private Deck deck;
    private LinkedList<Card> playerHand, bankerHand;
    private HashSet<Bet> playerBets, bankerBets, tieBets;
    
    @Override
    public void play(Player[] players, BigDecimal betMinimum,
            BigDecimal betMaximum) {
        this.players = players;
        this.betMinimum = betMinimum;
        this.betMaximum = betMaximum;
        deck = new Deck(8);
    }
    
    private void deal() {
        for (int i = 0; i < 2; i++) {
            playerHand.add(deck.dealCard());
            bankerHand.add(deck.dealCard());
        }
        
        if (isNatural(playerHand) || isNatural(bankerHand))
            return;
        
        if (getPlayerAction(playerHand) == Action.HIT)
            playerHand.add(deck.dealCard());
        
        if (getBankerAction(playerHand, bankerHand) == Action.HIT)
            bankerHand.add(deck.dealCard());
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
            default: return 0;
        }
    }
    
    /**
     * 
     * 
     * @param hand the hand whose value is being checked
     * @return the hand's value
     */
    private int getHandValue(LinkedList<Card> hand) {
        int total = 0;
        
        for (int i = 0; i < hand.size(); i++)
            total += getCardValue(hand.get(i));
        
        return total % 10;
    }
    
    private boolean isNatural(LinkedList<Card> hand) {
        return hand.size() == 2 && (getHandValue(hand) == 8 ||
                getHandValue(hand) == 9);
    }
    
    private LinkedList<Card> determineWinningHand(LinkedList<Card> playerHand,
            LinkedList<Card> bankerHand) {
        if (getHandValue(playerHand) < getHandValue(bankerHand))
            return bankerHand;
        if (getHandValue(playerHand) > getHandValue(bankerHand))
            return playerHand;
        return null; // as an indicator of a tie
    }
    
    /**
     * 
     * @param playerHand the player's hand
     * @return the action the player should take
     */
    private Action getPlayerAction(LinkedList<Card> playerHand) {
        if (getHandValue(playerHand) >= 5)
            return Action.HIT;
        else return Action.STAND;
    }
    
    /**
     * Must be called after getPlayerAction(playerHand) to work correctly.
     * 
     * @param bankerHand the banker's hand
     * @param playerHand the player's hand
     * @return the action the banker should take
     */
    private Action getBankerAction(LinkedList<Card> playerHand, LinkedList<Card>
            bankerHand) {
        if (playerHand.size() == 2) // that is, if player stood
            return getPlayerAction(bankerHand);
        else {
            int thirdCardValue = getCardValue(playerHand.get(2));
            
            /* This is actually a clever way to determine the correct banker
             * action if the player drew to limit the number of comparisons to
             * two--one at the beginning and one at the end.
             */
            if (thirdCardValue > 7)
                thirdCardValue -= 10;
            
            int bankerGoal = thirdCardValue / 2 + 3;
            
            if (getHandValue(bankerHand) <= bankerGoal)
                return Action.HIT;
            else return Action.STAND;
        }
    }
}
