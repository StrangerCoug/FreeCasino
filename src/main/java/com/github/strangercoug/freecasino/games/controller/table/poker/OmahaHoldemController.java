package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.OmahaHoldem;
import com.github.strangercoug.freecasino.games.view.table.poker.OmahaHoldemView;

public class OmahaHoldemController extends CommunityCardPokerController {
    protected OmahaHoldemController(OmahaHoldem model, OmahaHoldemView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
