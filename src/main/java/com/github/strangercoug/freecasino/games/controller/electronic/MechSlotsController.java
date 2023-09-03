package com.github.strangercoug.freecasino.games.controller.electronic;

import com.github.strangercoug.freecasino.games.model.electronic.MechSlots;
import com.github.strangercoug.freecasino.games.view.electronic.MechSlotsView;

public abstract class MechSlotsController extends SlotsController {
    protected MechSlotsController(MechSlots model, MechSlotsView view) {
        super(model, view);
    }
}
