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
 * Because of the need for a different wheel for American and European roulette,
 * this class is abstract. However, since they are otherwise very similar games,
 * most of the code will be here, with subclasses called only to handle the
 * differences between the games.
 * 
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public abstract class Roulette implements TableGame {
    protected Player[] players;
    protected BigDecimal betMinimum;
    protected BigDecimal betMaximum;
    protected Wheel wheel;
    
    protected enum Color {
        GREEN, RED, BLACK
    }
    
    /**
     * The stops of the roulette wheel. This will work for both American and
     * European roulette; the only difference is the use of the double zero and
     * the order of the stops.
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
        
        private Stop(String value, Color color) {
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
    }
    
    @Override
    public void play(Player[] players, BigDecimal betMinimum,
            BigDecimal betMaximum) {
        this.players = players;
        this.betMinimum = betMinimum;
        this.betMaximum = betMaximum;
        this.wheel = generateWheel();
    }
    
    public abstract Wheel generateWheel();
}
