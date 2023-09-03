package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.TripleDrawLo;
import com.github.strangercoug.freecasino.games.view.table.poker.TripleDrawLoView;

public class TripleDrawLoController extends DrawPokerController{
    protected TripleDrawLoController(TripleDrawLo model, TripleDrawLoView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
