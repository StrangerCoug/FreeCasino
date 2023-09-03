package com.github.strangercoug.freecasino.games.controller.electronic.poker;

import com.github.strangercoug.freecasino.games.model.electronic.poker.DeucesWild;
import com.github.strangercoug.freecasino.games.view.electronic.poker.DeucesWildView;

public class DeucesWildController extends VideoPokerController{
    protected DeucesWildController(DeucesWild model, DeucesWildView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
