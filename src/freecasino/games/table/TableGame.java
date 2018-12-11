/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.games.table;

import freecasino.Player;
import java.math.BigDecimal;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public interface TableGame {
    public void play(Player[] players, BigDecimal betMinimum,
            BigDecimal betMaximum);
}
