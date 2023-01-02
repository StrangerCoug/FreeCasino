/*
 * Copyright (c) 2018-2023, Jeffrey Hope
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted (subject to the limitations in the disclaimer
 * below) provided that the following conditions are met:
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
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY
 * THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.strangercoug.freecasino.games.table;

import com.github.strangercoug.freecasino.Game;
import com.github.strangercoug.freecasino.Player;
import com.github.strangercoug.freecasino.objs.BaccaratHand;
import com.github.strangercoug.freecasino.objs.Bet;
import java.math.BigDecimal;
import java.util.HashSet;
import com.github.strangercoug.freecasino.enums.Action;
import com.github.strangercoug.freecasino.objs.Deck;
import java.util.ArrayList;

/**
 * The class for a baccarat (more specifically, punto banco) game. The game
 * logic is designed not to care about at how big of a table the game is played;
 * therefore, it considers standard baccarat, midi baccarat, and mini baccarat
 * the same game. Determine which is which by checking the length of the player
 * array passed to it (usually 14 but sometimes 12 for the standard game, 9 for
 * midi, and 7 for mini).
 * 
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Baccarat extends Game implements TableGame {
	private ArrayList<Player> players;
	private BigDecimal betMinimum;
	private BigDecimal betMaximum;
	private Deck deck;
	private BaccaratHand playerHand, bankerHand;
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

	@Override
	public boolean isValidBet(Player player, BigDecimal bet) {
		return (bet.compareTo(player.getFunds()) < 1
				&& bet.compareTo(betMaximum) < 1
				&& bet.compareTo(betMinimum) > -1);
	}

	private void deal() {
		for (int i = 0; i < 2; i++) {
			playerHand.dealToHand(deck.dealCard());
			bankerHand.dealToHand(deck.dealCard());
		}

		if (playerHand.isNatural() || bankerHand.isNatural())
			return;

		if (getPlayerAction(playerHand) == Action.HIT)
			playerHand.dealToHand(deck.dealCard());

		if (getBankerAction(playerHand, bankerHand) == Action.HIT)
			bankerHand.dealToHand(deck.dealCard());
	}

	private BaccaratHand determineWinningHand(BaccaratHand playerHand,
	                                          BaccaratHand bankerHand) {
		if (playerHand.getHandValue() < bankerHand.getHandValue())
			return bankerHand;
		if (playerHand.getHandValue() > bankerHand.getHandValue())
			return playerHand;
		return null; // as an indicator of a tie
	}

	/**
	 * 
	 * @param playerHand the player's hand
	 * @return the action the player should take
	 */
	private Action getPlayerAction(BaccaratHand playerHand) {
		if (playerHand.getHandValue() >= 5)
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
	private Action getBankerAction(BaccaratHand playerHand,
	                               BaccaratHand bankerHand) {
		if (playerHand.getCardHand().size() == 2) // that is, if player stood
			return getPlayerAction(bankerHand);
		else {
			int thirdCardValue = playerHand.getCardHand().get(2).getPointValue() % 10;

			/* This is actually a clever way to determine the correct banker
			 * action if the player drew to limit the number of comparisons to
			 * two--one at the beginning and one at the end.
			 */
			if (thirdCardValue > 7)
				thirdCardValue -= 10;

			int bankerGoal = thirdCardValue / 2 + 3;

			if (bankerHand.getHandValue() <= bankerGoal)
				return Action.HIT;
			else return Action.STAND;
		}
	}
}
