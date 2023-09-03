package com.github.strangercoug.freecasino.games.controller.table;

import com.github.strangercoug.freecasino.games.controller.GameController;
import com.github.strangercoug.freecasino.games.model.table.Baccarat;
import com.github.strangercoug.freecasino.games.view.table.BaccaratView;

public class BaccaratController extends GameController {
    protected BaccaratController(Baccarat model, BaccaratView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
		throw new UnsupportedOperationException("Not supported yet.");
    }
}
