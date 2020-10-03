package com.gamedev.controller;

import com.gamedev.model.Model;
import com.gamedev.model.entity.Move;
import com.gamedev.model.entity.Player;

import java.util.ArrayList;

public class AIController {
    private Model model;

    public AIController(Model model) {
        this.model = model;
    }

    public Move computerTurn() {
        Player currentPlayer = model.getCurrentPlayer();
        Move move = getRandomPossibleMove(currentPlayer);
        model.placeDisc(move);
        return move;
    }

    private Move getRandomPossibleMove(Player currentPlayer) {
        ArrayList<Move> movesList = new ArrayList<>(model.getPossibleMoves(currentPlayer));
        return movesList.get(0);
    }
}
