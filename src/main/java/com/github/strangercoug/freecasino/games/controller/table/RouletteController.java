package com.github.strangercoug.freecasino.games.controller.table;

import com.github.strangercoug.freecasino.games.controller.GameController;
import com.github.strangercoug.freecasino.games.model.table.Roulette;
import com.github.strangercoug.freecasino.games.view.table.RouletteView;

public abstract class RouletteController extends GameController {
    protected RouletteController(Roulette model, RouletteView view) {
        super(model, view);
    }
}
