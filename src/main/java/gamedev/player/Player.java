package gamedev.player;

import gamedev.model.entity.Move;

public interface Player {
    Move getNextMove();

    gamedev.model.entity.Player getPlayerColour();
}
