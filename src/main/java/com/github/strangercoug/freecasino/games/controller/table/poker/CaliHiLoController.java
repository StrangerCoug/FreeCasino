package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.CaliHiLo;
import com.github.strangercoug.freecasino.games.view.table.poker.CaliHiLoView;

public class CaliHiLoController extends DrawPokerController {
    protected CaliHiLoController(CaliHiLo model, CaliHiLoView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
