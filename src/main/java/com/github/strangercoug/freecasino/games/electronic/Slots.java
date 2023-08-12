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
package com.github.strangercoug.freecasino.games.electronic;

import java.math.BigDecimal;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public abstract class Slots implements ElectronicGame {
	protected byte[] reelStops;
	protected int[][] payTable;
	protected int creditsIn;
	protected int creditsWon;
	protected int creditsBet;
	protected BigDecimal denomination;

	@Override
	public void insertCredits(BigDecimal funds) {
		BigDecimal credits = funds.multiply(denomination);
		creditsIn += credits.intValue();
	}

	@Override
	public void betCredits(int credits) {
		creditsBet = credits;
		creditsIn -= creditsBet;
	}

	@Override
	public void awardCredits(int credits) {
		creditsWon = credits;
		creditsIn += creditsWon;
	}

	@Override
	public BigDecimal ejectCredits() {
		BigDecimal ejectedCredits = denomination.multiply(BigDecimal.valueOf
				(creditsIn));
		creditsIn = 0;
		return ejectedCredits;
	}
}
