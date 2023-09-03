package com.github.strangercoug.freecasino.games.controller.electronic.poker;

import com.github.strangercoug.freecasino.games.model.electronic.poker.BonusPoker;
import com.github.strangercoug.freecasino.games.view.electronic.poker.BonusPokerView;

public class BonusPokerController extends VideoPokerController{
    protected BonusPokerController(BonusPoker model, BonusPokerView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
