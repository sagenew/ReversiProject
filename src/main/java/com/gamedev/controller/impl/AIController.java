package com.gamedev.controller.impl;

import com.gamedev.controller.AbstractPlayerController;
import com.gamedev.model.ReversiGame;
import com.gamedev.model.entity.Move;
import com.gamedev.model.entity.Player;

import java.util.ArrayList;

public class AIController implements AbstractPlayerController {
    private ReversiGame reversiGame;

    public AIController(ReversiGame reversiGame) {
        this.reversiGame = reversiGame;
    }

    @Override
    public Move makeTurn() {
        Player currentPlayer = reversiGame.getCurrentPlayer();
        Move move = getRandomPossibleMove(currentPlayer);
        reversiGame.placeDisc(move);
        return move;
    }

    private Move getRandomPossibleMove(Player currentPlayer) {
        ArrayList<Move> movesList = new ArrayList<>(reversiGame.getPossibleMoves(currentPlayer));
        return movesList.get(0);
    }
}
