package com.github.strangercoug.freecasino.games.controller.electronic.poker;

import com.github.strangercoug.freecasino.games.model.electronic.poker.JokerPoker;
import com.github.strangercoug.freecasino.games.view.electronic.poker.JokerPokerView;

public class JokerPokerController extends VideoPokerController{
    protected JokerPokerController(JokerPoker model, JokerPokerView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
