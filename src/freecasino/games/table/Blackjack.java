/*
 * Copyright (c) 2018, Jeffrey Hope
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the copyright holder nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package freecasino.games.table;

import freecasino.Game;
import freecasino.games.table.TableGame;
import freecasino.Player;
import freecasino.enums.CardRank;
import freecasino.objs.Bet;
import freecasino.objs.Card;
import freecasino.objs.Deck;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Blackjack extends Game implements TableGame {
    private ArrayList<Player> players;
    private BigDecimal betMinimum;
    private BigDecimal betMaximum;
    private Deck deck;
    private LinkedList<LinkedList<Card>> playerHands;
    private LinkedList<Card> dealerHand;
    private HashSet<Bet> bets;
            
    public void play(ArrayList<Player> players) {
        play(players, BigDecimal.valueOf(5, 2), BigDecimal.valueOf(1000, 2));
    }
    
    @Override
    public void play(ArrayList<Player> players, BigDecimal betMinimum,
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
