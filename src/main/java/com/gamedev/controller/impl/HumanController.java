package com.gamedev.controller.impl;

import com.gamedev.controller.AbstractPlayerController;
import com.gamedev.model.ReversiGame;
import com.gamedev.model.entity.Move;
import com.gamedev.model.entity.Player;
import com.gamedev.view.View;

import java.util.Scanner;

public class HumanController implements AbstractPlayerController {
    private ReversiGame reversiGame;
    private View view;
    private Scanner scanner;
    private Player currentPlayer;

    public HumanController(Player player, View view, ReversiGame reversiGame) {
        this.reversiGame = reversiGame;
        this.view = view;
        this.currentPlayer = player;
        scanner = new Scanner(System.in);
    }

    @Override
    public Move makeTurn() {
        Move move = null;
        String line;
        while (reversiGame.moveNotValid(move)) {
            view.playerMovePrompt(currentPlayer);
            line = scanner.nextLine();
            move = moveFromInput(line);
        }

        reversiGame.placeDisc(move);
        return move;
    }

    private Move moveFromInput(String line) {
        line = line.toUpperCase();
        if (inputMatchesFormat(line)) {
            return new Move(line.charAt(1) - '1', line.charAt(0) - 'A');
        } else {
            view.invalidMove();
            return null;
        }
    }

    private boolean inputMatchesFormat(String line) {
        return line.matches("^[A-H][1-8]$");
    }
}
