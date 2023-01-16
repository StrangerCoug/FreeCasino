package com.github.strangercoug.freecasino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class PlayerTest {
	private Player testPlayer;
	private final BigDecimal initialFunds = new BigDecimal(1000);
	private final BigDecimal transactionAmount = new BigDecimal("123.45");

	@BeforeEach
	void setTestPlayer() {
		testPlayer = new Player("Test", initialFunds);
	}

	@Test
	void testAddFunds() {
		testPlayer.addFunds(transactionAmount);
		assertThat(testPlayer.getFunds().toPlainString(), equalTo("1123.45"));
	}

	@Test
	void testSubtractFunds() {
		testPlayer.subtractFunds(transactionAmount);
		assertThat(testPlayer.getFunds().toPlainString(), equalTo("876.55"));
	}
}
