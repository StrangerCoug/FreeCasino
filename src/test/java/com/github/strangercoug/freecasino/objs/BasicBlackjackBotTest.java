package com.github.strangercoug.freecasino.objs;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class BasicBlackjackBotTest {
		@Test
		void TestEqualityContract() {
			EqualsVerifier.forClass(BasicBlackjackBot.class)
					.withRedefinedSuperclass()
					.suppress(Warning.NONFINAL_FIELDS) // the funds field needs to be mutable
					.verify();
		}
}
