package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.CrazyPineapple;
import com.github.strangercoug.freecasino.games.view.table.poker.CrazyPineappleView;

public class CrazyPineappleController extends CommunityCardPokerController {
    protected CrazyPineappleController(CrazyPineapple model, CrazyPineappleView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
