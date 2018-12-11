/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.games.poker;

import freecasino.Player;
import freecasino.games.poker.DrawPoker;
import java.math.BigDecimal;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public class KansasCityLoPoker extends DrawPoker {
    @Override
    public void play(Player[] players, BigDecimal betMinimum,
            BigDecimal betMaximum) {
        this.players = players;
        this.betMinimum = betMinimum;
        this.betMaximum = betMaximum;
    }
    
}
