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
package com.github.strangercoug.freecasino;

import com.github.strangercoug.freecasino.exceptions.InsufficientFundsException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Player {
	private final String name;
	private final boolean isHuman;
	private BigDecimal funds;

	public Player(String name, boolean isHuman, BigDecimal funds) {
		this.name = name;
		this.isHuman = isHuman;
		this.funds = funds.setScale(2, RoundingMode.HALF_EVEN);
	}

	public Player() {
		this("Anonymous", true, new BigDecimal(1000, MathContext.DECIMAL64));
	}
	public Player(String name) {
		this(name, true, new BigDecimal(1000, MathContext.DECIMAL64));
	}

	public Player(boolean isHuman) {
		this("Anonymous", isHuman, new BigDecimal(1000, MathContext.DECIMAL64));
	}

	public Player(BigDecimal funds) {
		this("Anonymous", true, funds);
	}

	public Player(String name, boolean isHuman) {
		this(name, isHuman, new BigDecimal(1000, MathContext.DECIMAL64));
	}

	public Player(String name, BigDecimal funds) {
		this(name, true, funds);
	}

	public Player(boolean isHuman, BigDecimal funds) {
		this("Anonymous", isHuman, funds);
	}

	public String getName() {
		return name;
	}

	public boolean isHuman() {
		return isHuman;
	}
	public BigDecimal getFunds() {
		return funds;
	}

	public void addFunds(BigDecimal fundsCredited) {
		funds = funds.add(fundsCredited);
	}

	public void subtractFunds(BigDecimal fundsDebited) {
		if (funds.subtract(fundsDebited).compareTo(BigDecimal.ZERO) < 0)
			throw new InsufficientFundsException();
		funds = funds.subtract(fundsDebited);
	}
}
