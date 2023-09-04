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
package com.github.strangercoug.freecasino.enums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PokerRankTest {

	@ParameterizedTest
	@MethodSource("pokerRankToString")
	void testPokerRank(PokerRank pokerRank, String string) {
		assertThat(pokerRank.toString(), equalTo(string));
	}

	private static Stream<Arguments> pokerRankToString() {
		return Stream.of(
			arguments(PokerRank.NOTHING, "Nothing"),
			arguments(PokerRank.ONE_PAIR, "One Pair"),
			arguments(PokerRank.TENS_OR_BETTER, "Tens or Better"),
			arguments(PokerRank.JACKS_OR_BETTER, "Jacks or Better"),
			arguments(PokerRank.TWO_PAIR, "Two Pair"),
			arguments(PokerRank.THREE_OF_A_KIND, "Three of a Kind"),
			arguments(PokerRank.STRAIGHT, "Straight"),
			arguments(PokerRank.FLUSH, "Flush"),
			arguments(PokerRank.FULL_HOUSE, "Full House"),
			arguments(PokerRank.FOUR_OF_A_KIND, "Four of a Kind"),
			arguments(PokerRank.FOUR_2S_THRU_4S, "Four 2-4"),
			arguments(PokerRank.FOUR_ACES, "Four Aces"),
			arguments(PokerRank.FOUR_2S_THRU_4S_WITH_ACE_THRU_4, "Four 2-4 with A-4"),
			arguments(PokerRank.FOUR_ACES_WITH_2_THRU_4, "Four Aces with 2-4"),
			arguments(PokerRank.STRAIGHT_FLUSH, "Straight Flush"),
			arguments(PokerRank.FIVE_OF_A_KIND, "Five of a Kind"),
			arguments(PokerRank.WILD_ROYAL, "Wild Royal Flush"),
			arguments(PokerRank.FOUR_DEUCES, "Four Deuces"),
			arguments(PokerRank.NATURAL_ROYAL, "Natural Royal Flush"),
			arguments(PokerRank.FIVE_WILDS, "Five Wilds")
		);
	}
}
