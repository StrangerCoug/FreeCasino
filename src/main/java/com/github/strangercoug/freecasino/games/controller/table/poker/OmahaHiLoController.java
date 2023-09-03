package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.OmahaHiLo;
import com.github.strangercoug.freecasino.games.view.table.poker.OmahaHiLoView;

public class OmahaHiLoController extends CommunityCardPokerController {
    protected OmahaHiLoController(OmahaHiLo model, OmahaHiLoView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
