/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.games.table;

import freecasino.games.table.TableGame;
import freecasino.Player;
import freecasino.objs.Wheel;
import java.math.BigDecimal;
/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public class BigSix implements TableGame {
    private enum Stop {
        ONE(1), TWO(2), FIVE(5), TEN(10), TWENTY(20), JOKER(40), LOGO(40);
        private final int value;
        
        private Stop(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    };
    
    private Player[] players;
    private BigDecimal betMinimum;
    private BigDecimal betMaximum;
    private Wheel wheel;

    @Override
    public void play(Player[] players, BigDecimal betMinimum,
            BigDecimal betMaximum) {
        this.players = players;
        this.betMinimum = betMinimum;
        this.betMaximum = betMaximum;
        
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
        
        wheel = new Wheel(stops);
    }
}
