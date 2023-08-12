package com.github.strangercoug.freecasino;

import com.github.strangercoug.freecasino.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {
	private Player testPlayer;
	private final BigDecimal initialFunds = new BigDecimal(1000);
	private final BigDecimal transactionAmount = new BigDecimal("123.45");
	private final BigDecimal excessiveTransactionAmount = new BigDecimal("1234.56");

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

	@Test
	void testSubtractFunds2() throws InsufficientFundsException {
		assertThrows(InsufficientFundsException.class, () -> testPlayer.subtractFunds(excessiveTransactionAmount));
	}
}
