package com.github.strangercoug.freecasino.games.controller.table;

import com.github.strangercoug.freecasino.games.model.table.AmericanRoulette;
import com.github.strangercoug.freecasino.games.view.table.AmericanRouletteView;

public class AmericanRouletteController extends RouletteController{
    protected AmericanRouletteController(AmericanRoulette model, AmericanRouletteView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
		throw new UnsupportedOperationException("Not supported yet.");
    }
}
