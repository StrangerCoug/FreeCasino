package com.github.strangercoug.freecasino.objs;

import com.github.strangercoug.freecasino.enums.CardRank;
import com.github.strangercoug.freecasino.enums.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class BaccaratHandTest {
    private BaccaratHand baccaratHand;

    @BeforeEach
    void initializeBaccaratHand() {
        baccaratHand = new BaccaratHand();
    }

    @Test
    void testIsNaturalEight() {
        baccaratHand.dealToHand(new Card(CardRank.NINE, CardSuit.SPADES));
        baccaratHand.dealToHand(new Card(CardRank.NINE, CardSuit.HEARTS));
        assertThat(baccaratHand.getHandValue(), equalTo(8));
        assertThat(baccaratHand.isNatural(), equalTo(true));
    }

    @Test
    void testIsNaturalNine() {
        baccaratHand.dealToHand(new Card(CardRank.TEN, CardSuit.SPADES));
        baccaratHand.dealToHand(new Card(CardRank.NINE, CardSuit.HEARTS));
        assertThat(baccaratHand.getHandValue(), equalTo(9));
        assertThat(baccaratHand.isNatural(), equalTo(true));
    }


    @Test
    void testIsNaturalFalseTwoCards() {
        baccaratHand.dealToHand(new Card(CardRank.TEN, CardSuit.SPADES));
        baccaratHand.dealToHand(new Card(CardRank.SEVEN, CardSuit.HEARTS));
        assertThat(baccaratHand.getHandValue(), equalTo(7));
        assertThat(baccaratHand.isNatural(), equalTo(false));
    }

    @Test
    void testIsNaturalFalseThreeCards() {
        baccaratHand.dealToHand(new Card(CardRank.THREE, CardSuit.SPADES));
        baccaratHand.dealToHand(new Card(CardRank.THREE, CardSuit.HEARTS));
        baccaratHand.dealToHand(new Card(CardRank.THREE, CardSuit.DIAMONDS));
        assertThat(baccaratHand.getHandValue(), equalTo(9));
        assertThat(baccaratHand.isNatural(), equalTo(false));
    }

    @Test
    void testIsPair() {
        baccaratHand.dealToHand(new Card(CardRank.TEN, CardSuit.SPADES));
        baccaratHand.dealToHand(new Card(CardRank.TEN, CardSuit.HEARTS));
        assertThat(baccaratHand.isPair(), equalTo(true));
    }


    @Test
    void testIsPairDifferentRanksSameValue() {
        baccaratHand.dealToHand(new Card(CardRank.TEN, CardSuit.SPADES));
        baccaratHand.dealToHand(new Card(CardRank.JACK, CardSuit.HEARTS));
        assertThat(baccaratHand.isPair(), equalTo(false));
    }

    @Test
    void testIsPairThreeCards() {
        baccaratHand.dealToHand(new Card(CardRank.TEN, CardSuit.SPADES));
        baccaratHand.dealToHand(new Card(CardRank.TEN, CardSuit.HEARTS));
        baccaratHand.dealToHand(new Card(CardRank.THREE, CardSuit.DIAMONDS));

        /* Note that isPair returns true even though the third card isn't the same rank as the other two--it is
         * completely irrelevant
         */
        assertThat(baccaratHand.isPair(), equalTo(true));
    }
}
