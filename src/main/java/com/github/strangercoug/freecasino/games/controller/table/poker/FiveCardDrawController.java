package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.FiveCardDraw;
import com.github.strangercoug.freecasino.games.view.table.poker.FiveCardDrawView;

public class FiveCardDrawController extends DrawPokerController {
    protected FiveCardDrawController(FiveCardDraw model, FiveCardDrawView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
