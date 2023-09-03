package com.github.strangercoug.freecasino.games.controller.electronic;

import com.github.strangercoug.freecasino.games.controller.GameController;
import com.github.strangercoug.freecasino.games.model.electronic.Slots;
import com.github.strangercoug.freecasino.games.view.electronic.SlotsView;

public abstract class SlotsController extends GameController {
    protected SlotsController(Slots model, SlotsView view) {
        super(model, view);
    }
}
