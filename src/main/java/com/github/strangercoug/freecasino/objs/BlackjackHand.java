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
package com.github.strangercoug.freecasino.objs;

import com.github.strangercoug.freecasino.enums.CardRank;

public class BlackjackHand extends CardHand {

	/**
	 *
	 *
	 * @param card the card whose value is being checked
	 * @return the card's value
	 */
	private int getCardValue(Card card) {
		return switch (card.rank()) {
			case ACE -> 1;
			case TWO -> 2;
			case THREE -> 3;
			case FOUR -> 4;
			case FIVE -> 5;
			case SIX -> 6;
			case SEVEN -> 7;
			case EIGHT -> 8;
			case NINE -> 9;
			default -> 10;
		};
	}

	/* TODO: Determine if there is a more efficient way to calculate a hand's
	 * value and whether it's hard or soft than the next three methods.
	 */

	/**
	 * Determines the value of a hand for the purposes of the game, correctly
	 * treating an ace as 11 points when a hand is soft.
	 *
	 * @return the hand's value
	 */
	public int getValue() {
		int total = getHardValue();

		if (total <= 11) {
			for (Card card : hand) {
				if (card.rank() == CardRank.ACE) {
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
	 * @return the hand's value
	 */
	private int getHardValue() {
		int total = 0;

		for (Card card : hand) total += getCardValue(card);

		return total;
	}

	/**
	 * Determines whether the hand is a soft hand, i.e. has an ace that can
	 * safely be counted as 11 points instead of 1 point.
	 *
	 * @return true if the hand is soft, false otherwise
	 */
	public boolean isSoft() {
		return getValue() == getHardValue() + 10;
	}

	/**
	 * Do not check for this method after splitting aces.
	 */
	public boolean isBlackjack() {
		return hand.size() == 2 && getValue() == 21;
	}

	// TODO: Also allow restrictions based on player hand.
	public boolean canDouble() {
		return hand.size() == 2;
	}

	public boolean canSplit() {
		if (hand.size() != 2)
			return false;
		return getCardValue(hand.get(0)) == getCardValue(hand.get(1));
	}

	// TODO: Also allow restrictions based on dealer upcard or completely disabling surrendering.
	public boolean canSurrender() {
		return hand.size() == 2;
	}
}
