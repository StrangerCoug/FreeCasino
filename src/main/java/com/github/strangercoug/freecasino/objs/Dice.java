/*
 * Copyright (c) 2018-2023, Jeffrey Hope
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted (subject to the limitations in the disclaimer
 * below) provided that the following conditions are met:
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
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY
 * THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.strangercoug.freecasino.objs;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Dice {
	private final int[] dice;
	private final int SIDES;

	/**
	 * Creates a number of dice.
	 * 
	 * @param number the number of dice to create
	 * @param sides the number of sides per die
	 */
	public Dice(int number, int sides) {
		this.dice = new int[number];
		this.SIDES = sides;
	}

	/**
	 * Creates a number of six-sided dice.
	 * 
	 * @param number the number of dice to create
	 */
	public Dice(int number) {
		this(number, 6);
	}

	/**
	 * Creates two six-sided dice.
	 */
	public Dice() {
		this(2, 6);
	}

	public int[] getDice() {
		int[] theseDice = new int[dice.length];

		System.arraycopy(dice, 0, theseDice, 0, dice.length);

		return theseDice;
	}

	/**
	 * 
	 * @param index the die from which to return the face
	 * @return the face value
	 */
	public int getDieFace(int index) {
		return dice[index];
	}

	/**
	 * 
	 * @return the sum of the dice 
	 */
	public int getTotal() {
		int total = 0;

		for (int i = 0; i < dice.length; i++)
			total += getDieFace(i);

		return total;
	}

	/**
	 * Rolls the dice.
	 */
	public void rollDice() {
		for (int i = 0; i < dice.length; i++)
			dice[i] = (int) (Math.random() * SIDES + 1);
	}
}
