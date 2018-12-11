/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.games.table;

import freecasino.games.table.Roulette;
import freecasino.objs.Wheel;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
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
