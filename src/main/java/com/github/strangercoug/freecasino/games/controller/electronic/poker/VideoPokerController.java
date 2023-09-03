package com.github.strangercoug.freecasino.games.controller.electronic.poker;

import com.github.strangercoug.freecasino.games.controller.PokerController;
import com.github.strangercoug.freecasino.games.model.electronic.poker.VideoPoker;
import com.github.strangercoug.freecasino.games.view.electronic.poker.VideoPokerView;

public abstract class VideoPokerController extends PokerController {
    protected VideoPokerController(VideoPoker model, VideoPokerView view) {
        super(model, view);
    }
}
