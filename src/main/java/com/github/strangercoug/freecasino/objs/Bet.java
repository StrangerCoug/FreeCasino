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
package com.github.strangercoug.freecasino.objs;

import com.github.strangercoug.freecasino.Player;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Bet {
    private final Player player;
    private BigDecimal bet;
    
    public Bet (Player player, BigDecimal bet) {
        this.player = player;
        this.bet = bet;
        bet.setScale(2, RoundingMode.HALF_EVEN);
        player.subtractFunds(bet);
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public BigDecimal getBet() {
        return bet;
    }
    
    /**
     * Adds an amount to an existing bet. To make a new bet that can be won or
     * lost independently of an existing bet, create a new Bet object.
     * 
     * @param betAdded the amount to add to the bet 
     */
    public void increaseBet(BigDecimal betAdded) {
        bet = bet.add(betAdded);
        player.subtractFunds(betAdded);
    }
    
    /**
     * Subtracts an amount from an existing bet.
     * 
     * @param betRemoved the amount to subtract from the bet
     */
    public void decreaseBet(BigDecimal betRemoved) {
        bet = bet.subtract(betRemoved);
        player.subtractFunds(betRemoved);
    }
    
    /**
     * Returns how much a player wins with his or her bet, including the stake. This
     * does not automatically add the winnings to the player's total; to do that,
     * call Player.addFunds(Bet.awardBet(odds)).
     * 
     * @param odds the exact odds of the bet
     * @return the amount the player won
     */
    
    public BigDecimal awardBet(BigDecimal odds) {
        BigDecimal winnings = bet.multiply(odds, MathContext.UNLIMITED);
        return winnings.setScale(2, RoundingMode.HALF_EVEN);
    }
}
