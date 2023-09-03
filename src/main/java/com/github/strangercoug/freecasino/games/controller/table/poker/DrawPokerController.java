package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.controller.PokerController;
import com.github.strangercoug.freecasino.games.model.table.poker.DrawPoker;
import com.github.strangercoug.freecasino.games.view.table.poker.DrawPokerView;

public abstract class DrawPokerController extends PokerController {
    protected DrawPokerController(DrawPoker model, DrawPokerView view) {
        super(model, view);
    }
}
