package com.gamedev.controller;

import com.gamedev.model.Model;
import com.gamedev.model.entity.Move;
import com.gamedev.model.entity.Player;
import com.gamedev.view.ConsoleView;

import java.util.Scanner;

public class Controller {
    private Model model;
    private ConsoleView view;
    private Scanner scanner;
    private Player player;
    private AIController aiController;
    private boolean showHints;

    public Controller() {
        model = new Model();
        view = new ConsoleView();
        scanner = new Scanner(System.in);
        aiController = new AIController(model);
    }

    public void startGame() {
        view.startGameMenu();
        setupGame();
        for(;;) {
            gameLoop();
            gameOverMenu();
        }
    }

    private void gameLoop() {
        Player currentPlayer;
        Move lastComputerMove = null;
        while (model.gameNotFinished()) {
            currentPlayer = model.getCurrentPlayer();
            if (isPlayerTurn(currentPlayer)) {
                view.clearScreen();
                view.printGameBoard(model.getGameBoard(showHints));
                view.gameScoreMessage(model.getDiscsCount());
                view.computerMoveMessage(lastComputerMove);
                Move move = getPlayerMove(currentPlayer);
                model.placeDisc(move);
            } else {
                lastComputerMove = aiController.computerTurn();
            }
        }

        view.gameOverMessage(model.getWinner());
    }

    private Move getPlayerMove(Player currentPlayer) {
        Move move = null;
        String line;
        while (model.moveNotValid(move)) {
            view.playerMovePrompt(currentPlayer);
            line = scanner.nextLine();
            move = moveFromInput(line);
        }
        return move;
    }

    private void setupGame() {
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                chooseColor();
                model.initGame();
                break;
            case "2":
                player = Player.PVP;
                model.initGame();
                break;
            case "3":
                showHints = !showHints;
                view.hintsOptionMessage(showHints);
                view.chooseModeMenu();
                setupGame();
                break;
            case "4": System.exit(0);
            default:
                view.invalidOptionMenu();
                view.chooseModeMenu();
                setupGame();
                break;
        }
    }

    private void gameOverMenu() {
        view.continueGameOrAbort();
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                startGame();
                break;
            case "2":
                System.exit(0);
            default:
                view.invalidOptionMenu();
                gameOverMenu();
                break;
        }
    }

    private void chooseColor() {
        view.chooseColorMenu();

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                player = Player.BLACK;
                break;
            case "2":
                player = Player.WHITE;
                break;
            default:
                view.invalidOptionMenu();
                chooseColor();
                break;
        }
    }

    private boolean isPlayerTurn(Player currentPlayer) {
        return player.equals(currentPlayer) || player.equals(Player.PVP);
    }

    private Move moveFromInput(String line) {
        line = line.toUpperCase();
        if(inputMatchesFormat(line)) {
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
