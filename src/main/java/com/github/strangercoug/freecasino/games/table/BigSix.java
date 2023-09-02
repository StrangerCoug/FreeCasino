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
package com.github.strangercoug.freecasino.games.table;

import com.github.strangercoug.freecasino.Game;
import com.github.strangercoug.freecasino.Player;
import com.github.strangercoug.freecasino.objs.Wheel;
import java.math.BigDecimal;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class BigSix extends Game implements TableGame {
	private BigDecimal betMinimum;
	private BigDecimal betMaximum;
	private enum Stop {
		ONE(1), TWO(2), FIVE(5), TEN(10), TWENTY(20), JOKER(40), LOGO(40);
		private final int value;

		Stop(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	@Override
	public void play() {
		play(BigDecimal.valueOf(5, 2), BigDecimal.valueOf(1000, 2));
	}

	@Override
	public void play(BigDecimal betMinimum,	BigDecimal betMaximum) {

		Stop[] stops = {Stop.LOGO, Stop.TWO, Stop.ONE, Stop.TWO, Stop.ONE, 
				Stop.TWO, Stop.ONE, Stop.TEN, Stop.ONE, Stop.FIVE, Stop.ONE,
				Stop.TWO, Stop.ONE, Stop.TWENTY, Stop.ONE, Stop.TWO, Stop.ONE,
				Stop.FIVE, Stop.TWO, Stop.ONE, Stop.TEN, Stop.ONE, Stop.TWO,
				Stop.FIVE, Stop.ONE, Stop.TWO, Stop.ONE, Stop.JOKER, Stop.ONE,
				Stop.TWO, Stop.ONE, Stop.FIVE, Stop.TWO, Stop.ONE, Stop.TEN,
				Stop.ONE, Stop.FIVE, Stop.ONE, Stop.TWO, Stop.ONE, Stop.TWENTY,
				Stop.ONE, Stop.TWO, Stop.ONE, Stop.FIVE, Stop.TWO, Stop.ONE,
				Stop.TEN, Stop.ONE, Stop.TWO, Stop.FIVE, Stop.ONE, Stop.TWO,
				Stop.ONE};

		Wheel wheel = new Wheel(stops);
	}

	@Override
	public boolean isValidBet(Player player, BigDecimal bet) {
		return (bet.compareTo(player.getFunds()) < 1
				&& bet.compareTo(betMaximum) < 1
				&& bet.compareTo(betMinimum) > -1);
	}
}
