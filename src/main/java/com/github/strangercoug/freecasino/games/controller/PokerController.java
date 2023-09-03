package com.github.strangercoug.freecasino.games.controller;

import com.github.strangercoug.freecasino.games.model.Poker;
import com.github.strangercoug.freecasino.games.view.PokerView;

public abstract class PokerController extends GameController{
    protected PokerController(Poker model, PokerView view) {
        super(model, view);
    }
}
