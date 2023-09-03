package com.github.strangercoug.freecasino.games.controller.electronic.poker;

import com.github.strangercoug.freecasino.games.model.electronic.poker.DeucesAndJokerWild;
import com.github.strangercoug.freecasino.games.view.electronic.poker.DeucesAndJokerWildView;

public class DeucesAndJokerWildController extends VideoPokerController{
    protected DeucesAndJokerWildController(DeucesAndJokerWild model, DeucesAndJokerWildView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
