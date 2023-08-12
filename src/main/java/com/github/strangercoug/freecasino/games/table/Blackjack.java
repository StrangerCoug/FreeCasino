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
import com.github.strangercoug.freecasino.objs.Bet;
import com.github.strangercoug.freecasino.objs.BlackjackHand;
import com.github.strangercoug.freecasino.objs.Deck;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Blackjack extends Game implements TableGame {
	private List<Player> players;
	private BigDecimal betMinimum;
	private BigDecimal betMaximum;
	private Deck deck;
	private LinkedList<BlackjackHand> playerHands;
	private BlackjackHand dealerHand;
	private HashSet<Bet> bets;

	@Override
	public void play(List<Player> players) {
		play(players, BigDecimal.valueOf(5, 2), BigDecimal.valueOf(1000, 2));
	}

	@Override
	public void play(List<Player> players, BigDecimal betMinimum,
			BigDecimal betMaximum) {
		this.players = players;
		this.betMinimum = betMinimum;
		this.betMaximum = betMaximum;

		/* TODO: This is just for testing purposes; there should be an option to
		 * control this. Ideally, we'd want to read from an option file.
		 */
		deck = new Deck(6);
		bets = new HashSet<>(players.size());

		playerHands = new LinkedList<>();
		dealerHand = new BlackjackHand();

		for (Player player : players) {
			playerHands.add(new BlackjackHand());
		}
	}

	@Override
	public boolean isValidBet(Player player, BigDecimal bet) {
		return (bet.compareTo(player.getFunds()) < 1
				&& bet.compareTo(betMaximum) < 1
				&& bet.compareTo(betMinimum) > -1);
	}
}
