package com.github.strangercoug.freecasino.games.controller.table;

import com.github.strangercoug.freecasino.games.controller.GameController;
import com.github.strangercoug.freecasino.games.model.table.Craps;
import com.github.strangercoug.freecasino.games.view.table.CrapsView;

public class CrapsController extends GameController {
    protected CrapsController(Craps model, CrapsView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
		throw new UnsupportedOperationException("Not supported yet.");
    }
}
