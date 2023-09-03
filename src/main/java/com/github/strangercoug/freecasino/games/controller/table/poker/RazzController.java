package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.Razz;
import com.github.strangercoug.freecasino.games.view.table.poker.RazzView;

public class RazzController extends StudPokerController {
    protected RazzController(Razz model, RazzView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
