package com.github.strangercoug.freecasino.games.controller.electronic;

import com.github.strangercoug.freecasino.games.controller.GameController;
import com.github.strangercoug.freecasino.games.model.electronic.Keno;
import com.github.strangercoug.freecasino.games.view.electronic.KenoView;

public class KenoController extends GameController {
    protected KenoController(Keno model, KenoView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
		throw new UnsupportedOperationException("Not supported yet.");
    }
}
