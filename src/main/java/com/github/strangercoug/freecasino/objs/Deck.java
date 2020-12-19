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
package com.github.strangercoug.freecasino.objs;

import com.github.strangercoug.freecasino.enums.CardRank;
import com.github.strangercoug.freecasino.enums.CardSuit;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Deck {
	protected LinkedList<Card> deck;
	protected final int NUM_DECKS;
	private final boolean USES_BLACK_JOKER;
	private final boolean USES_RED_JOKER;

	public Deck(int numDecks, boolean usesBlackJoker, boolean usesRedJoker) {
		deck = new LinkedList<>();
		NUM_DECKS = numDecks;
		USES_BLACK_JOKER = usesBlackJoker;
		USES_RED_JOKER = usesRedJoker;
	}

	public Deck(int numDecks) {
		this(numDecks, false, false);
	}

	public Deck() {
		this(1, false, false);
	}

	public void populateDeck() {
		CardRank[] ranks = {CardRank.TWO, CardRank.THREE, CardRank.FOUR,
		        CardRank.FIVE, CardRank.SIX, CardRank.SEVEN, CardRank.EIGHT,
		        CardRank.NINE, CardRank.TEN, CardRank.JACK, CardRank.QUEEN,
		        CardRank.KING, CardRank.ACE};
		CardSuit[] suits = {CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.HEARTS,
		        CardSuit.SPADES};

		for (int i = 0; i < NUM_DECKS; i++) {
			for (int j = 0; j < 52; i++)
				deck.add(new Card(ranks[i/4], suits[i%4]));

			if (USES_BLACK_JOKER)
				deck.add(new Card(CardRank.JOKER, CardSuit.BLACK));

			if (USES_RED_JOKER)
				deck.add(new Card(CardRank.JOKER, CardSuit.RED));
		}
	}

	/* TODO: This is fine for alpha and beta testing, but at a later point I
	 * would like to be able to detect whether there is an Internet connection
	 * and use the random.org API to shuffle if possible. If something goes
	 * wrong, we fall back to this.
	 */
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

	public Card dealCard() {
		return deck.pop();
	}
}
