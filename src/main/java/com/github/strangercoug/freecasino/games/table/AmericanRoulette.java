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

import com.github.strangercoug.freecasino.objs.Wheel;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class AmericanRoulette extends Roulette {
	@Override
	public Wheel generateWheel () {
		Stop[] stops = {Stop.ZERO, Stop.TWENTY_EIGHT, Stop.NINE,
			Stop.TWENTY_SIX, Stop.THIRTY, Stop.ELEVEN, Stop.SEVEN, Stop.TWENTY,
			Stop.THIRTY_TWO, Stop.SEVENTEEN, Stop.FIVE, Stop.TWENTY_TWO,
			Stop.THIRTY_FOUR, Stop.FIFTEEN, Stop.THREE, Stop.TWENTY_FOUR,
			Stop.THIRTY_SIX, Stop.THIRTEEN, Stop.ONE, Stop.DOUBLE_ZERO,
			Stop.TWENTY_SEVEN, Stop.TEN, Stop.TWENTY_FIVE, Stop.TWENTY_NINE,
			Stop.TWELVE, Stop.EIGHT, Stop.NINETEEN, Stop.THIRTY_ONE,
			Stop.EIGHTEEN, Stop.SIX, Stop.TWENTY_ONE, Stop.THIRTY_THREE,
			Stop.SIXTEEN, Stop.FOUR, Stop.TWENTY_THREE, Stop.THIRTY_FIVE,
			Stop.FOURTEEN, Stop.TWO};

		return new Wheel(stops);
	}
}
