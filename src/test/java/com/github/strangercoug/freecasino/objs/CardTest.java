package com.github.strangercoug.freecasino.objs;

import com.github.strangercoug.freecasino.enums.CardRank;
import com.github.strangercoug.freecasino.enums.CardSuit;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardTest {
    private static LinkedList<Card> cards;

    @BeforeAll
    static void setCards() {
        cards = new LinkedList<>();
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
    void testOutranksDifferentRank() {
        Card card1 = new Card(CardRank.TWO, CardSuit.SPADES);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);
        assertThat(card2.outranks(card1), equalTo(true));
    }

    @Test
    void testOutranksDifferentSuit() {
        Card card1 = new Card(CardRank.ACE, CardSuit.CLUBS);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);

        // even though card2.compareTo(card1) > 0
        assertThat(card2.outranks(card1), equalTo(false));
    }

    @Test
    void testOutranksNull() {
        Card card = new Card(CardRank.ACE, CardSuit.CLUBS);
        assertThrows(NullPointerException.class, () -> card.outranks(null));
    }

    @Test
    void testCompareToSameCard() {
        Card card1 = new Card(CardRank.ACE, CardSuit.SPADES);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);
        assertThat(card1.compareTo(card2), equalTo(0));
    }

    @Test
    void testCompareToDifferentRank() {
        Card card1 = new Card(CardRank.TWO, CardSuit.SPADES);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);
        assertThat(card2.compareTo(card1), greaterThan(0));
    }


    @Test
    void testCompareToDifferentSuit() {
        Card card1 = new Card(CardRank.ACE, CardSuit.CLUBS);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);
        assertThat(card2.compareTo(card1), greaterThan(0));
    }

    @Test
    void testCompareToNull() {
        Card card = new Card(CardRank.ACE, CardSuit.CLUBS);
        assertThrows(NullPointerException.class, () -> card.compareTo(null));
    }

    @Test
    void testEqualsContract() {
        EqualsVerifier.forClass(Card.class).verify();
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
