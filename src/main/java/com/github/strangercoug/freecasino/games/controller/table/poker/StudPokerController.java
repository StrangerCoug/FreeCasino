package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.controller.PokerController;
import com.github.strangercoug.freecasino.games.model.table.poker.StudPoker;
import com.github.strangercoug.freecasino.games.view.table.poker.StudPokerView;

public abstract class StudPokerController extends PokerController {
    protected StudPokerController(StudPoker model, StudPokerView view) {
        super(model, view);
    }
}
