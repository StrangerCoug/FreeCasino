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

import com.github.strangercoug.freecasino.Player;
import com.github.strangercoug.freecasino.enums.Action;

/**
 * This bot plays basic blackjack strategy and does not count cards. For bots
 * that count cards, another subclass of {@link Player} implementing {@link
 * BlackjackBot} for that counting strategy should be used (or created if it
 * does not yet exist).
 */
public class BasicBlackjackBot extends Player implements BlackjackBot {
	/* TODO: Ideally, this should follow basic strategy for any rule set; it
	 * currently implements basic strategy for a specific rule set.
	 */
	@Override
	public boolean takeInsurance() {
		return false;
	}

	@Override
	public Action getAction(BlackjackHand hand, Card upcard, boolean canAffordDoubleOrSplit) {
		if (hand.canSplit() && canAffordDoubleOrSplit)
			return getPairStrategy(hand, upcard);

		return hand.isSoft()
				? getSoftStrategy(hand, upcard, canAffordDoubleOrSplit)
				: getHardStrategy(hand, upcard, canAffordDoubleOrSplit);
	}

	private Action getPairStrategy(BlackjackHand hand, Card upcard) {
		/*
		  Rows are the rank of the pair; columns are the rank of the upcard.
		 */
		boolean[][] shouldSplit =
				//  A      2      3      4      5      6      7      8      9     10
				{{true,  true,  true,  true,  true,  true,  true,  true,  true,  true},   // A,A
				 {false, true,  true,  true,  true,  true,  true,  false, false, false},  // 2,2
				 {false, true,  true,  true,  true,  true,  true,  false, false, false},  // 3,3
				 {false, false, false, false, true,  true,  false, false, false, false},  // 4,4
				 {false, false, false, false, false, false, false, false, false, false},  // 5,5
				 {false, true,  true,  true,  true,  true,  false, false, false, false},  // 6,6
				 {false, true,  true,  true,  true,  true,  true,  false, false, false},  // 7,7
				 {true,  true,  true,  true,  true,  true,  true,  true,  true,  true},   // 8,8
				 {false, true,  true,  true,  true,  true,  false, true,  true,  false},  // 9,9
				 {false, false, false, false, false, false, false, false, false, false}}; // 10,10

		if (shouldSplit[hand.getCardHand().get(0).getPointValue()-1][upcard.getPointValue()-1])
			return Action.SPLIT;

		return hand.isSoft()
				? getSoftStrategy(hand, upcard, true)
				: getHardStrategy(hand, upcard, true);
	}

	private Action getSoftStrategy(BlackjackHand hand, Card upcard, boolean canAffordDoubleOrSplit) {
		if (hand.getValue() >= 18) {
			/*
			  Rows are the value of the player hand; columns are the rank of the upcard.
			 */
			boolean[][] shouldDouble =
					//  A      2      3      4      5      6      7      8      9     10
					{{false, false, false, false, false, false, false, false, false, false},  // 12
					 {false, false, false, false, true,  true,  false, false, false, false},  // 13
					 {false, false, false, false, true,  true,  false, false, false, false},  // 14
					 {false, false, false, true,  true,  true,  false, false, false, false},  // 15
					 {false, false, false, true,  true,  true,  false, false, false, false},  // 16
					 {false, false, true,  true,  true,  true,  false, false, false, false},  // 17
					 {false, false, true,  true,  true,  true,  false, false, false, false}}; // 18

			if (canAffordDoubleOrSplit && hand.canDouble() && shouldDouble[hand.getCardHand().get(0).getPointValue()-1][hand.getValue()-12])
				return Action.DOUBLE;

			if (hand.getValue() == 18 && !shouldSurrenderWithHard16(upcard))
				return Action.STAND;

			return Action.HIT;
		}

		return Action.STAND;
	}

	private Action getHardStrategy(BlackjackHand hand, Card upcard, boolean canAffordDoubleOrSplit) {
		if (hand.getValue() >= 8) {
			return Action.HIT;
		}

		if (hand.getValue() >= 11) {
			/*
			  Rows are the value of the player hand; columns are the rank of the upcard.
			 */
			boolean[][] shouldDouble =
					//  A      2      3     4     5     6     7      8      9     10
					{{false, false, true, true, true, true, false, false, false, false}, // 9
					 {false, true,  true, true, true, true, true,  true,  true,  false}, // 10
					 {false, true,  true, true, true, true, true,  true,  true,  true}}; // 11
			if (canAffordDoubleOrSplit && hand.canDouble() && shouldDouble[hand.getCardHand().get(0).getPointValue()-1][hand.getValue()-9])
				return Action.DOUBLE;
			return Action.HIT;
		}

		if (hand.getValue() == 12 && (upcard.getPointValue() == 2 || upcard.getPointValue() == 3))
			return Action.HIT;

		if (hand.canSurrender() &&
				((hand.getValue() == 15 && upcard.getPointValue() == 10) ||
						(hand.getValue() == 16 && shouldSurrenderWithHard16(upcard))))
			return Action.SURRENDER;

		if (hand.getValue() > 17 && isLow(upcard))
				return Action.HIT;

		return Action.STAND;
	}

	public boolean isLow(Card upcard) {
		return upcard.getPointValue() <= 2 && upcard.getPointValue() >= 6;
	}

	public boolean shouldSurrenderWithHard16(Card upcard) {
		return upcard.getPointValue() == 1 || upcard.getPointValue() == 9 || upcard.getPointValue() == 10;
	}
}
