/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.games.electronic;

import java.math.BigDecimal;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public interface ElectronicGame {
    public void insertCredits(BigDecimal funds);
    public void betCredits(int credits);
    public void awardCredits(int credits);
    public BigDecimal ejectCredits();
}
