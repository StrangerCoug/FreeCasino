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
import com.github.strangercoug.freecasino.objs.Player;
import com.github.strangercoug.freecasino.objs.Bet;
import com.github.strangercoug.freecasino.objs.Dice;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Craps extends Game implements TableGame {
	private BigDecimal betMinimum;
	private BigDecimal betMaximum;
	private Dice dice;
	private HashSet<Bet> bets;
	private int shooterIndex;
	/**
	 * This should always be null on the come out roll.
	 */
	private Point currentPoint;

	/**
	 * One-roll bets that are the equivalent of several one-roll bets placed simultaneously are not listed here.
	 * Use a method to place the corresponding equivalents instead.
	 */
	@Getter
	private enum BetType {
		PASS(new BigDecimal(2)),
		DONT_PASS(new BigDecimal(2)),
		/**
		 * Cannot be bet on the come out roll.
		 */
		COME(new BigDecimal(2)),
		/**
		 * Cannot be bet directly. Come bets are moved here if the next roll is a 4.
		 */
		COME_4(new BigDecimal(2)),
		/**
		 * Cannot be bet directly. Come bets are moved here if the next roll is a 5.
		 */
		COME_5(new BigDecimal(2)),
		/**
		 * Cannot be bet directly. Come bets are moved here if the next roll is a 6.
		 */
		COME_6(new BigDecimal(2)),
		/**
		 * Cannot be bet directly. Come bets are moved here if the next roll is an 8.
		 */
		COME_8(new BigDecimal(2)),
		/**
		 *  Cannot be bet directly. Come bets are moved here if the next roll is a 9.
		 */
		COME_9(new BigDecimal(2)),
		/**
		 *  Cannot be bet directly. Come bets are moved here if the next roll is a 10.
		 */
		COME_10(new BigDecimal(2)),
		/**
		 * Cannot be bet on the come out roll.
		 */
		DONT_COME(new BigDecimal(2)),
		/**
		 * Cannot be bet directly. Don't Come bets are moved here if the next roll is a 4.
		 */
		DONT_COME_4(new BigDecimal(2)),
		/**
		 * Cannot be bet directly. Don't Come bets are moved here if the next roll is a 5.
		 */
		DONT_COME_5(new BigDecimal(2)),
		/**
		 * Cannot be bet directly. Don't Come bets are moved here if the next roll is a 6.
		 */
		DONT_COME_6(new BigDecimal(2)),
		/**
		 * Cannot be bet directly. Don't Come bets are moved here if the next roll is an 8.
		 */
		DONT_COME_8(new BigDecimal(2)),
		/**
		 * Cannot be bet directly. Don't Come bets are moved here if the next roll is a 9.
		 */
		DONT_COME_9(new BigDecimal(2)),
		/**
		 * Cannot be bet directly. Don't Come bets are moved here if the next roll is a 10.
		 */
		DONT_COME_10(new BigDecimal(2)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Buy 4 bet.
		 */
		ODDS_4(new BigDecimal(3)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Buy 5 bet.
		 */
		ODDS_5(new BigDecimal(5).divide(new BigDecimal(2), RoundingMode.UNNECESSARY)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Buy 6 bet.
		 */
		ODDS_6(new BigDecimal(11).divide(new BigDecimal(5), RoundingMode.UNNECESSARY)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Buy 8 bet.
		 */
		ODDS_8(new BigDecimal(11).divide(new BigDecimal(5), RoundingMode.UNNECESSARY)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Buy 9 bet.
		 */
		ODDS_9(new BigDecimal(5).divide(new BigDecimal(2), RoundingMode.UNNECESSARY)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Buy 10 bet.
		 */
		ODDS_10(new BigDecimal(3)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Lay 4 bet.
		 */
		ODDS_AGAINST_4(new BigDecimal(3).divide(new BigDecimal(2), RoundingMode.UNNECESSARY)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Lay 5 bet.
		 */
		ODDS_AGAINST_5(new BigDecimal(5).divide(new BigDecimal(3), RoundingMode.UNNECESSARY)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Lay 6 bet.
		 */
		ODDS_AGAINST_6(new BigDecimal(11).divide(new BigDecimal(6), RoundingMode.UNNECESSARY)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Lay 8 bet.
		 */
		ODDS_AGAINST_8(new BigDecimal(11).divide(new BigDecimal(6), RoundingMode.UNNECESSARY)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Lay 9 bet.
		 */
		ODDS_AGAINST_9(new BigDecimal(5).divide(new BigDecimal(3), RoundingMode.UNNECESSARY)),
		/**
		 * This minus the value returned from {@code calculateVig} is also used for the Lay 10 bet.
		 */
		ODDS_AGAINST_10(new BigDecimal(3).divide(new BigDecimal(2), RoundingMode.UNNECESSARY)),
		PLACE_4(new BigDecimal(14).divide(new BigDecimal(5), RoundingMode.UNNECESSARY)),
		PLACE_5(new BigDecimal(12).divide(new BigDecimal(5), RoundingMode.UNNECESSARY)),
		PLACE_6(new BigDecimal(13).divide(new BigDecimal(6), RoundingMode.UNNECESSARY)),
		PLACE_8(new BigDecimal(13).divide(new BigDecimal(6), RoundingMode.UNNECESSARY)),
		PLACE_9(new BigDecimal(12).divide(new BigDecimal(5), RoundingMode.UNNECESSARY)),
		PLACE_10(new BigDecimal(14).divide(new BigDecimal(5), RoundingMode.UNNECESSARY)),
		PLACE_4_TO_LOSE(new BigDecimal(16).divide(new BigDecimal(11), RoundingMode.UNNECESSARY)),
		PLACE_5_TO_LOSE(new BigDecimal(13).divide(new BigDecimal(8), RoundingMode.UNNECESSARY)),
		PLACE_6_TO_LOSE(new BigDecimal(9).divide(new BigDecimal(5), RoundingMode.UNNECESSARY)),
		PLACE_8_TO_LOSE(new BigDecimal(9).divide(new BigDecimal(5), RoundingMode.UNNECESSARY)),
		PLACE_9_TO_LOSE(new BigDecimal(13).divide(new BigDecimal(8), RoundingMode.UNNECESSARY)),
		PLACE_10_TO_LOSE(new BigDecimal(16).divide(new BigDecimal(11), RoundingMode.UNNECESSARY)),
		FIELD(new BigDecimal(2)),
		FIELD_2(new BigDecimal(3)),
		FIELD_12(new BigDecimal(4)),
		BIG_6(new BigDecimal(2)),
		BIG_8(new BigDecimal(2)),
		HARD_4(new BigDecimal(10)),
		HARD_6(new BigDecimal(8)),
		HARD_8(new BigDecimal(8)),
		HARD_10(new BigDecimal(10)),
		TWO(new BigDecimal(31)),
		THREE(new BigDecimal(16)),
		SEVEN(new BigDecimal(5)),
		ELEVEN(new BigDecimal(16)),
		TWELVE(new BigDecimal(31)),
		CRAPS(new BigDecimal(8));

		/**
		 * Because of the way {@code Bet.awardBet()} works; this field holds the
		 * odds "for 1", not the odds "to 1" (i.e. "2 for 1" in lieu of "1 to 1").
		 */
		private final BigDecimal odds;

		BetType(BigDecimal odds) {
			this.odds = odds;
		}

	}

	private enum Point{
		FOUR(4),
		FIVE(5),
		SIX(6),
		EIGHT(8),
		NINE(9),
		TEN(10);

		private final int pointValue;

		Point(int pointValue) {
			this.pointValue = pointValue;
		}

		int getPointValue() {
			return pointValue;
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
		dice = new Dice();
		shooterIndex = 0;
		currentPoint = null;
	}

	private void shoot() {
		dice.rollDice();

		if (currentPoint == null) {
			if (winsOnComeOut(dice)) {
				/* TODO: Pay the bets on the pass line,
				 * collect the bets on the don't pass line
				 */
			} else if (isCraps(dice)) {
				/* TODO: Pay the bets on the don't pass line (except push on 12),
				 * collect the bets on the pass line
				 */
			} else {
				// Set the point
				switch (dice.getTotal()) {
					case 4 -> currentPoint = Point.FOUR;
					case 5 -> currentPoint = Point.FIVE;
					case 6 -> currentPoint = Point.SIX;
					case 8 -> currentPoint = Point.EIGHT;
					case 9 -> currentPoint = Point.NINE;
					case 10 -> currentPoint = Point.TEN;
					default -> throw new IllegalStateException();
				}
			}
		} else {
			if (dice.getTotal() == currentPoint.getPointValue()) {
				/* TODO: Pay the bets on the pass line,
				 * collect the bets on the don't pass line
				 */
				currentPoint = null;
			} else if (dice.getTotal() == 7) {
				/* TODO: Pay the bets on the don't pass line,
				 * collect the bets on the pass line
				 */
				currentPoint = null;
				advanceShooter();
			}
		}
	}

	@Override
	public boolean isValidBet(Player player, BigDecimal bet) {
		return (bet.compareTo(player.getFunds()) < 1
				&& bet.compareTo(betMaximum) < 1
				&& bet.compareTo(betMinimum) > -1);
	}

	private boolean isCraps(Dice dice) {
		return dice.getTotal() == 2 || dice.getTotal() == 3 || dice.getTotal() == 12;
	}

	private boolean winsOnComeOut(Dice dice) {
		return dice.getTotal() == 7 || dice.getTotal() == 11;
	}

	private void advanceShooter() {
		shooterIndex = (shooterIndex + 1) % getPlayers().size();
	}

	/**
	 * This should be subtracted from a buy or lay bet before paying true odds on the original bet.
	 *
	 * @return 5% of {@code amountBet}, rounded to the nearest dollar (half rounds down)
	 */
	private BigDecimal calculateVig(BigDecimal amountBet) {
		if (amountBet.compareTo(BigDecimal.valueOf(20)) < 0)
			return BigDecimal.ONE;
		return amountBet.divide(BigDecimal.valueOf(20), 0, RoundingMode.HALF_DOWN);
	}
}
