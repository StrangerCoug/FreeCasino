package com.github.strangercoug.freecasino.games.controller.table.poker;

import com.github.strangercoug.freecasino.games.controller.PokerController;
import com.github.strangercoug.freecasino.games.model.table.poker.CommunityCardPoker;
import com.github.strangercoug.freecasino.games.view.table.poker.CommunityCardPokerView;

public abstract class CommunityCardPokerController extends PokerController {
    protected CommunityCardPokerController(CommunityCardPoker model, CommunityCardPokerView view) {
        super(model, view);
    }
}
