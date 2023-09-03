package com.github.strangercoug.freecasino.games.controller.table;

import com.github.strangercoug.freecasino.games.controller.GameController;
import com.github.strangercoug.freecasino.games.model.table.EuropeanRoulette;
import com.github.strangercoug.freecasino.games.view.table.EuropeanRouletteView;

public class EuropeanRouletteController extends GameController {
    protected EuropeanRouletteController(EuropeanRoulette model, EuropeanRouletteView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
		throw new UnsupportedOperationException("Not supported yet.");
    }
}
