package gamedev.player.strategy;

import gamedev.model.entity.Move;
import gamedev.model.entity.Player;

public interface AbstractStrategy {
    Move chooseNextMove(int[][] board, Player player);
}
