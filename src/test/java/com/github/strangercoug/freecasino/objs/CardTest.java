package com.github.strangercoug.freecasino.objs;

import com.github.strangercoug.freecasino.enums.CardRank;
import com.github.strangercoug.freecasino.enums.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class CardTest {
    private LinkedList<Card> cards;

    @BeforeEach
    void setCards() {
        cards = new LinkedList<Card>();
        CardRank[] ranks = new CardRank[]{CardRank.TWO, CardRank.THREE, CardRank.FOUR, CardRank.FIVE, CardRank.SIX,
                CardRank.SEVEN, CardRank.EIGHT, CardRank.NINE, CardRank.TEN, CardRank.JACK, CardRank.QUEEN, CardRank.KING,
                CardRank.ACE, CardRank.JOKER, CardRank.JOKER};
        CardSuit[] suits = new CardSuit[]{CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.SPADES, CardSuit.CLUBS,
                CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.SPADES, CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.HEARTS,
                CardSuit.SPADES, CardSuit.CLUBS, CardSuit.BLACK, CardSuit.RED};
        for (int i = 0; i < ranks.length; i++) {
            cards.add(new Card(ranks[i], suits[i]));
        }
    }

    @Test
    void testGetPointValue() {
        int[] expectedValues = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 1, 10, 10};
        for (int i = 0; i < expectedValues.length; i++) {
            assertThat(cards.get(i).getPointValue(), equalTo(expectedValues[i]));
        }
    }

    @Test
    void testToString() {
        String[] expectedValues = new String[]{"Two of Clubs", "Three of Diamonds", "Four of Hearts", "Five of Spades",
                "Six of Clubs", "Seven of Diamonds", "Eight of Hearts", "Nine of Spades", "Ten of Clubs", "Jack of Diamonds",
                "Queen of Hearts", "King of Spades", "Ace of Clubs", "Black Joker", "Red Joker"};
        for (int i = 0; i < expectedValues.length; i++) {
            assertThat(cards.get(i).toString(), equalTo(expectedValues[i]));
        }
    }
}
