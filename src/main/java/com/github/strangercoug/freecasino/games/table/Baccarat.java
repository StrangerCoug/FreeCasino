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
package com.github.strangercoug.freecasino.games.table;

import com.github.strangercoug.freecasino.Game;
import com.github.strangercoug.freecasino.Player;
import com.github.strangercoug.freecasino.objs.Bet;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import com.github.strangercoug.freecasino.enums.Action;
import com.github.strangercoug.freecasino.objs.Card;
import com.github.strangercoug.freecasino.objs.Deck;
import java.util.ArrayList;

/**
 * The class for a baccarat (more specifically, punto banco) game. The game
 * logic is designed not to care about at how big of a table the game is played;
 * therefore, it considers standard baccarat, midi baccarat, and mini baccarat
 * the same game. Which is which is to be determined by checking the length of
 * the player array passed to it (usually 14 but sometimes 12 for the standard
 * game, 9 for midi, and 7 for mini).
 * 
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Baccarat extends Game implements TableGame {
	private ArrayList<Player> players;
	private BigDecimal betMinimum;
	private BigDecimal betMaximum;
	private Deck deck;
	private LinkedList<Card> playerHand, bankerHand;
	private HashSet<Bet> playerBets, bankerBets, tieBets;
	
	@Override
	public void play(ArrayList<Player> players) {
		play(players, BigDecimal.valueOf(5, 2), BigDecimal.valueOf(1000, 2));
	}
	
	@Override
	public void play(ArrayList<Player> players, BigDecimal betMinimum,
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
