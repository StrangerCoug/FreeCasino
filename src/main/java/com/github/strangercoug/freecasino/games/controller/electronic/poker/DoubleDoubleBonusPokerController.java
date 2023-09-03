package com.github.strangercoug.freecasino.games.controller.electronic.poker;

import com.github.strangercoug.freecasino.games.model.electronic.poker.DoubleDoubleBonusPoker;
import com.github.strangercoug.freecasino.games.view.electronic.poker.DoubleDoubleBonusPokerView;

public class DoubleDoubleBonusPokerController extends VideoPokerController{
    protected DoubleDoubleBonusPokerController(DoubleDoubleBonusPoker model, DoubleDoubleBonusPokerView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
