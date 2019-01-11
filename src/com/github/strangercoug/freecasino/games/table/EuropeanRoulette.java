/*
 * Copyright (c) 2018, Jeffrey Hope
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
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
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.strangercoug.freecasino.games.table;

import com.github.strangercoug.freecasino.objs.Wheel;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class EuropeanRoulette extends Roulette {
    @Override
    public Wheel generateWheel () {
        Stop[] stops = {Stop.ZERO, Stop.THIRTY_TWO, Stop.FIFTEEN, Stop.NINETEEN,
            Stop.FOUR, Stop.TWENTY_ONE, Stop.TWO, Stop.TWENTY_FIVE,
            Stop.SEVENTEEN, Stop.THIRTY_FOUR, Stop.SIX, Stop.TWENTY_SEVEN,
            Stop.THIRTEEN, Stop.THIRTY_SIX, Stop.ELEVEN, Stop.THIRTY,
            Stop.EIGHT, Stop.TWENTY_THREE, Stop.TEN, Stop.FIVE,
            Stop.TWENTY_FOUR, Stop.SIXTEEN, Stop.THIRTY_THREE, Stop.ONE,
            Stop.TWENTY, Stop.FOURTEEN, Stop.THIRTY_ONE, Stop.NINE,
            Stop.TWENTY_TWO, Stop.EIGHTEEN, Stop.TWENTY_NINE, Stop.SEVEN,
            Stop.TWENTY_EIGHT, Stop.TWELVE, Stop.THIRTY_FIVE, Stop.THREE,
            Stop.TWENTY_SIX};
        
        return new Wheel(stops);
    }
}
