package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.CaliLo;
import com.github.strangercoug.freecasino.games.view.table.poker.CaliLoView;

public class CaliLoController extends DrawPokerController {
    protected CaliLoController(CaliLo model, CaliLoView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
