package com.github.strangercoug.freecasino.games.controller.electronic.poker;

import com.github.strangercoug.freecasino.games.model.electronic.poker.TensOrBetter;
import com.github.strangercoug.freecasino.games.view.electronic.poker.TensOrBetterView;

public class TensOrBetterController extends VideoPokerController{
    protected TensOrBetterController(TensOrBetter model, TensOrBetterView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
