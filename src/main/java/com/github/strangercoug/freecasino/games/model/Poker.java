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
package com.github.strangercoug.freecasino.games.model;

import com.github.strangercoug.freecasino.enums.CardRank;
import com.github.strangercoug.freecasino.objs.Card;
import com.github.strangercoug.freecasino.objs.Deck;
import java.math.BigDecimal;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public abstract class Poker extends Game {
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
		FIVE_OF_A_KIND
	}

	private int[] countRanks(Card[] hand) {
		int[] cardsPerRank = new int[14];

		for (Card hand1 : hand) {
			cardsPerRank[hand1.rank().ordinal()]++;
		}

		return cardsPerRank;
	}

	public boolean isFiveOfAKind(Card[] hand) {
		int[] cardsPerRank = countRanks(hand);

		return switch (cardsPerRank[CardRank.JOKER.ordinal()]) {
			case 1 -> isFourOfAKind(hand);
			case 2 -> isThreeOfAKind(hand);
			default -> false;
		};
	}

	private boolean isFourOfAKind(Card[] hand) {
		int[] cardsPerRank = countRanks(hand);

		for (int j : cardsPerRank)
			if (j == 4)
				return true;

		return false;
	}

	public boolean isFlush(Card[] hand) {
		for (int i = 1; i < hand.length; i++)
			if (!hand[i].suit().equals(hand[i-1].suit()))
				return false;

		return true;
	}

	private boolean isThreeOfAKind(Card[] hand) {
		int[] cardsPerRank = countRanks(hand);

		for (int j : cardsPerRank)
			if (j == 3)
				return true;

		return false;
	}

	public boolean isPair(Card[] hand) {
		int[] cardsPerRank = countRanks(hand);

		for (int j : cardsPerRank)
			if (j == 2)
				return true;

		return false;
	}
}
