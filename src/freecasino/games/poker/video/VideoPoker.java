/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.games.poker.video;

import freecasino.games.poker.Poker;
import freecasino.games.electronic.ElectronicGame;
import freecasino.objs.Card;
import java.math.BigDecimal;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public abstract class VideoPoker extends Poker implements ElectronicGame {
    protected Card[] hand;
    protected int[][] payTable;
    protected int creditsIn, creditsWon, creditsBet;
    protected BigDecimal denomination;

    @Override
    public void insertCredits(BigDecimal funds) {
        BigDecimal credits = funds.multiply(denomination);
        creditsIn = credits.intValue();
    }

    @Override
    public void betCredits(int credits) {
        creditsBet = credits;
        creditsIn -= creditsBet;
    }

    @Override
    public void awardCredits(int credits) {
        creditsWon = credits;
        creditsIn += creditsWon;
    }

    @Override
    public BigDecimal ejectCredits() {
        BigDecimal ejectedCredits = denomination.multiply(BigDecimal.valueOf
                (creditsIn));
        creditsIn = 0;
        return ejectedCredits;
    }
}
