package com.github.strangercoug.freecasino.games.controller.electronic.poker;

import com.github.strangercoug.freecasino.games.model.electronic.poker.JacksOrBetter;
import com.github.strangercoug.freecasino.games.view.electronic.poker.JacksOrBetterView;

public class JacksOrBetterController extends VideoPokerController{
    protected JacksOrBetterController(JacksOrBetter model, JacksOrBetterView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
