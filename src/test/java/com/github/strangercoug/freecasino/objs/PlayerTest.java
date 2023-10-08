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

import com.github.strangercoug.freecasino.exceptions.InsufficientFundsException;
import com.github.strangercoug.freecasino.objs.BasicBlackjackBot;
import com.github.strangercoug.freecasino.objs.Player;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
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

	@Test
	void TestEqualityContract() {
		EqualsVerifier.forClass(Player.class)
				.withRedefinedSubclass(BasicBlackjackBot.class)
				.suppress(Warning.NONFINAL_FIELDS) // the funds field needs to be mutable
				.verify();
	}
}
