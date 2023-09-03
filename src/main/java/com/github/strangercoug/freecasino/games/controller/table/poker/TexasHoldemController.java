package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.model.table.poker.TexasHoldem;
import com.github.strangercoug.freecasino.games.view.table.poker.TexasHoldemView;

public class TexasHoldemController extends CommunityCardPokerController {
    protected TexasHoldemController(TexasHoldem model, TexasHoldemView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
