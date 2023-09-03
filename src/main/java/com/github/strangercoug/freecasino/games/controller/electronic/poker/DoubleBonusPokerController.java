package com.github.strangercoug.freecasino.games.controller.electronic.poker;

import com.github.strangercoug.freecasino.games.model.electronic.poker.DoubleBonusPoker;
import com.github.strangercoug.freecasino.games.view.electronic.poker.DoubleBonusPokerView;

public class DoubleBonusPokerController extends VideoPokerController{
    protected DoubleBonusPokerController(DoubleBonusPoker model, DoubleBonusPokerView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
