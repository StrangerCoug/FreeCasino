package com.github.strangercoug.freecasino.games.controller.table;

import com.github.strangercoug.freecasino.games.controller.GameController;
import com.github.strangercoug.freecasino.games.model.table.Blackjack;
import com.github.strangercoug.freecasino.games.view.table.BlackjackView;

public class BlackjackController extends GameController {
    protected BlackjackController(Blackjack model, BlackjackView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
		throw new UnsupportedOperationException("Not supported yet.");
    }
}
