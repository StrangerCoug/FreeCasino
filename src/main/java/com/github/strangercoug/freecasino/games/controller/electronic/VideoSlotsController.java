package com.github.strangercoug.freecasino.games.controller.electronic;

import com.github.strangercoug.freecasino.games.model.electronic.VideoSlots;
import com.github.strangercoug.freecasino.games.view.electronic.VideoSlotsView;

public abstract class VideoSlotsController extends SlotsController {
    protected VideoSlotsController(VideoSlots model, VideoSlotsView view) {
        super(model, view);
    }
}
