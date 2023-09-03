package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.KansasCityLo;
import com.github.strangercoug.freecasino.games.view.table.poker.KansasCityLoView;

public class KansasCityLoController extends DrawPokerController{
    protected KansasCityLoController(KansasCityLo model, KansasCityLoView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
