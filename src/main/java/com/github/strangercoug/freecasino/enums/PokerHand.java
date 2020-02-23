/*
 * Copyright (c) 2018-2020, Jeffrey Hope
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
package com.github.strangercoug.freecasino.enums;

/**
 * I originally wrote this enum for a Deuces Wild minigame for Telnaior's
 * Race to a Billion bot at https://github.com/Telnaior/RtaB-Bot. It is expanded
 * here to allow for a wider variety of hands to be recognized. Some of these
 * hands are still meant only for video poker.
 * 
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */

// TODO: Decide how to handle names that should differ depending on the game.
public enum PokerHand {
	NOTHING("Nothing"), // couldn't think of any other good name
	ONE_PAIR("One Pair"),
	TENS_OR_BETTER("Tens or Better"),
	JACKS_OR_BETTER("Jacks or Better"),
	TWO_PAIR("Two Pair"),
	THREE_OF_A_KIND("Three of a Kind"),
	STRAIGHT("Straight"), /* A-2-3-4-5 and 10-J-Q-K-A both count, even though
	                       * aces are normally high */
	FLUSH("Flush"),
	FULL_HOUSE("Full House"),
	FOUR_OF_A_KIND("Four of a Kind"),
	FOUR_2S_THRU_4S("Four 2-4"),
	FOUR_ACES("Four Aces"),
	FOUR_2S_THRU_4S_WITH_ACE_THRU_4("Four 2-4 with A-4"),
	FOUR_ACES_WITH_2_THRU_4("Four Aces with 2-4"),
	STRAIGHT_FLUSH("Straight Flush"), /* A-2-3-4-5 counts, but not 10-J-Q-K-A --
	                                   * that's one of the royal hands */
	FIVE_OF_A_KIND("Five of a Kind"), // Only possible with wild cards
	WILD_ROYAL("Wild Royal Flush"),
	FOUR_DEUCES("Four Deuces"),
	NATURAL_ROYAL("Natural Royal Flush"),
	FIVE_WILDS("Five Wilds");
	
	private final String name;
	
	PokerHand (String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
