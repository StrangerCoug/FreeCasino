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
package com.github.strangercoug.freecasino.games.model.table;

import com.github.strangercoug.freecasino.games.model.Game;
import com.github.strangercoug.freecasino.Player;
import com.github.strangercoug.freecasino.objs.Wheel;
import java.math.BigDecimal;

/**
 * Because of the need for a different wheel for American and European roulette,
 * this class is abstract. However, since they are otherwise very similar games,
 * most of the code will be here, with subclasses called only to handle the
 * differences between the games.
 * 
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public abstract class Roulette extends Game implements TableGame {
	protected BigDecimal betMinimum;
	protected BigDecimal betMaximum;
	protected Wheel wheel;
	protected Stop result;

	protected enum Color {
		GREEN, RED, BLACK
	}

	/**
	 * The stops of the roulette wheel. This will work for both American and
	 * European roulette; the only difference is whether double zero is used and
	 * the order of the stops.
	 * <p>
	 * The zeroes have special handling in this enum, see the
	 * {@code AmericanRoulette} and {@code EuropeanRoulette} classes for
	 * details.
	 */
	protected enum Stop {
		ZERO("0", Color.GREEN), DOUBLE_ZERO("00", Color.GREEN),
		ONE("1", Color.RED), TWO("2", Color.BLACK), THREE("3", Color.RED),
		FOUR("4", Color.BLACK), FIVE("5", Color.RED), SIX("6", Color.BLACK),
		SEVEN("7", Color.RED), EIGHT("8", Color.BLACK), NINE("9", Color.RED),
		TEN("10", Color.BLACK), ELEVEN("11", Color.BLACK),
		TWELVE("12", Color.RED), THIRTEEN("13", Color.BLACK),
		FOURTEEN("14", Color.RED), FIFTEEN("15", Color.BLACK),
		SIXTEEN("16", Color.RED), SEVENTEEN("17", Color.BLACK),
		EIGHTEEN("18", Color.RED), NINETEEN("19", Color.RED),
		TWENTY("20", Color.BLACK), TWENTY_ONE("21", Color.RED),
		TWENTY_TWO("22", Color.BLACK), TWENTY_THREE("23", Color.RED),
		TWENTY_FOUR("24", Color.BLACK), TWENTY_FIVE("25", Color.RED),
		TWENTY_SIX("26", Color.BLACK), TWENTY_SEVEN("27", Color.RED),
		TWENTY_EIGHT("28", Color.BLACK), TWENTY_NINE("29", Color.BLACK),
		THIRTY("30", Color.RED), THIRTY_ONE("31", Color.BLACK),
		THIRTY_TWO("32", Color.RED), THIRTY_THREE("33", Color.BLACK),
		THIRTY_FOUR("34", Color.RED), THIRTY_FIVE("35", Color.BLACK),
		THIRTY_SIX("36", Color.RED);
		private final String value;
		private final Color color;

		Stop(String value, Color color) {
			this.value = value;
			this.color = color;
		}

		public String getValue() {
			return value;
		}

		public Color getColor() {
			return color;
		}

		public int getValueAsInt() {
			return Integer.parseInt(value);
		}

		/**
		 * 
		 * @return -1 if a zero space, 0 if even, 1 if odd
		 */
		public int getParity() {
			if (color == Color.GREEN)
				return -1;
			return Integer.parseInt(value) % 2;
		}

		public int getColumn() {
			if (color == Color.GREEN)
				return -1;
			return Integer.parseInt(value) % 3;
		}

		public int getDozen() {
			if (color == Color.GREEN)
				return -1;
			return (Integer.parseInt(value) - 1) / 12;
		}

		public int getHalf() {
			if (color == Color.GREEN)
				return -1;
			return (Integer.parseInt(value) - 1) / 18;	
		}

		public int getStreet() {
			if (color == Color.GREEN)
				return -1;
			return (int)Math.ceil(Integer.parseInt(value) / 3.0) - 1;
		}
	}

	/**
	 * For legibility, this enum combines certain types of bets together that has
	 * the same odds. For example, bets of 0-1-2, 0-00-2, and 0/00-2-3 are special
	 * cases of the corner bets, not street bets; but in the internal logic they are
	 * counted as street bets. For the same reason, 0-1-2-3 is treated as a corner
	 * bet despite technically being a form of the double street bet.
	 */
	protected enum betType{
		STRAIGHT(new BigDecimal(36)), SPLIT(new BigDecimal(18)),
		STREET(new BigDecimal(12)), CORNER(new BigDecimal(9)),
		BASKET(new BigDecimal(7)), DOUBLE_STREET(new BigDecimal(6)),
		DOZEN(new BigDecimal(3)), HALF(new BigDecimal(2));

		/**
		 * Because of the way {@code Bet.awardBet()} works; this field holds the
		 * odds "for 1", not the odds "to 1" (i.e. "2 for 1" in lieu of "1 to 1").
		 */
		private final BigDecimal odds;

		betType(BigDecimal odds) {
			this.odds = odds;
		}

		public BigDecimal getOdds() {
			return odds;
		}
	}

	@Override
	public void play() {
		play(BigDecimal.valueOf(5, 2), BigDecimal.valueOf(1000, 2));
	}

	@Override
	public void play(BigDecimal betMinimum, BigDecimal betMaximum) {
		this.betMinimum = betMinimum;
		this.betMaximum = betMaximum;
		wheel = generateWheel();
		result = (Stop)wheel.getWheelResult();
	}

	@Override
	public boolean isValidBet(Player player, BigDecimal bet) {
		return (bet.compareTo(player.getFunds()) < 1
				&& bet.compareTo(betMaximum) < 1
				&& bet.compareTo(betMinimum) > -1);
	}
	
	public abstract Wheel generateWheel();
}
