package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.SevenCardStudHiLo;
import com.github.strangercoug.freecasino.games.view.table.poker.SevenCardStudHiLoView;

public class SevenCardStudHiLoController extends StudPokerController {
    protected SevenCardStudHiLoController(SevenCardStudHiLo model, SevenCardStudHiLoView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
