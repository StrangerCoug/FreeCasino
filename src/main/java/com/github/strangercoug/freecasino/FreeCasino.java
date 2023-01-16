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
package com.github.strangercoug.freecasino;

import com.github.strangercoug.freecasino.games.Keno;
import com.github.strangercoug.freecasino.games.table.Baccarat;
import com.github.strangercoug.freecasino.games.table.BigSix;
import com.github.strangercoug.freecasino.games.table.Blackjack;
import com.github.strangercoug.freecasino.games.table.Craps;
import com.github.strangercoug.freecasino.games.table.RedDog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class FreeCasino {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean validInput = false, playAgain = false;
		String entry;
		Game game = null;
		ArrayList<Player> players;

		while (true) {
			do {
				System.out.println("""
						Select game to play or type "QUIT" to  quit:
						1. Baccarat
						2. Big Six
						3. Blackjack
						4. Craps
						5. Keno
						6. Poker
						7. Red Dog
						8. Roulette
						9. Video Poker""");
				entry = input.nextLine();
				if (entry.equalsIgnoreCase("quit")) {
					input.close();
					System.exit(0);
				}
				try {
					int gameSelected = Integer.parseInt(entry);
					if (gameSelected >= 1 && gameSelected <= 9)
						validInput = true;
					game = returnGame(gameSelected);
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid input.");
				}
				catch (IllegalArgumentException e) {
					System.out.println("Invalid game number.");
				}
			} while (!validInput);

			validInput = false;
			int numPlayers = 0;
			do {
				try {
					System.out.print("How many players would you like? ");
					entry = input.nextLine();
					numPlayers = Integer.parseInt(entry);
					if (numPlayers < 1) {
						System.out.println("You must enter a positive "
								+ "integer.");
					}
				}
				catch(NumberFormatException e) {
					System.out.print("Invalid input.");
				}
			} while (numPlayers < 1);
			players = new ArrayList<>(numPlayers);
			for (int i = 1; i <= numPlayers; i++) {
				System.out.print("Enter name of player #" + i + ": ");
				entry = input.nextLine();
				players.add(new Player(entry));
			}
			System.out.println("Good luck!");

			do {
				game.play(players);
				validInput = false;
				do {
					System.out.print("Play again? (Y/N): ");
					char selection = input.nextLine().charAt(0);
					switch (selection) {
						case 'Y', 'y' -> {
							validInput = true;
							playAgain = true;
						}
						case 'N', 'n' -> {
							validInput = true;
							playAgain = false;
						}
						default -> System.out.println("Invalid selection.");
					}
				} while (!validInput);
			} while (playAgain);
		}
	}

	private static Game returnGame(int i) {
		return switch (i) {
			case 1 -> new Baccarat();
			case 2 -> new BigSix();
			case 3 -> new Blackjack();
			case 4 -> new Craps();
			case 5 -> new Keno(); // Poker
			case 6, 7 -> new RedDog(); // Roulette
			// Video Poker
			case 8, 9, default -> throw new IllegalArgumentException();
		};
	}
}
