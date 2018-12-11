/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.games.table;

import freecasino.games.table.TableGame;
import freecasino.Player;
import java.math.BigDecimal;
        
/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public class Craps implements TableGame {
    private Player[] players;
    private BigDecimal betMinimum;
    private BigDecimal betMaximum;

    @Override
    public void play(Player[] players, BigDecimal betMinimum,
            BigDecimal betMaximum) {
        this.players = players;
        this.betMinimum = betMinimum;
        this.betMaximum = betMaximum;
    }
    
}
