/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.games.table;

import freecasino.objs.Wheel;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
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
