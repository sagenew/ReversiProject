package gamedev.player.impl;

import gamedev.model.ReversiGame;
import gamedev.model.entity.Move;
import gamedev.model.entity.Player;
import gamedev.player.strategy.AbstractStrategy;

public class AIPlayer implements gamedev.player.Player {
    ReversiGame reversiGame;
    Player player;
    AbstractStrategy strategy;

    public AIPlayer(AbstractStrategy strategy, ReversiGame reversiGame, Player player) {
        this.reversiGame = reversiGame;
        this.player = player;
        this.strategy = strategy;
    }

    @Override
    public Move getNextMove() {
        return strategy.chooseNextMove(reversiGame.getGameBoardCopy(), getPlayerColour());
    }

    @Override
    public Player getPlayerColour() {
        return player;
    }
}
