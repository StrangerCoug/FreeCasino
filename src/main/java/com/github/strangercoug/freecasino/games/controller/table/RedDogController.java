package com.github.strangercoug.freecasino.games.controller.table;

import com.github.strangercoug.freecasino.games.controller.GameController;
import com.github.strangercoug.freecasino.games.model.table.RedDog;
import com.github.strangercoug.freecasino.games.view.table.RedDogView;

public class RedDogController extends GameController {
    protected RedDogController(RedDog model, RedDogView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
		throw new UnsupportedOperationException("Not supported yet.");
    }
}
