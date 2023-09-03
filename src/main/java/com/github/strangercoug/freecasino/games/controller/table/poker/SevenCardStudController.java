package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.SevenCardStud;
import com.github.strangercoug.freecasino.games.view.table.poker.SevenCardStudView;

public class SevenCardStudController extends StudPokerController {
    protected SevenCardStudController(SevenCardStud model, SevenCardStudView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
