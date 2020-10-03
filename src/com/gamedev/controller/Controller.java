package com.gamedev.controller;

import com.gamedev.model.Model;
import com.gamedev.model.entity.Move;
import com.gamedev.model.entity.Player;
import com.gamedev.view.ConsoleView;

import java.util.Scanner;
import java.util.Set;

public class Controller {
    Model model;
    ConsoleView view;
    Scanner scanner;
    Player player;

    public Controller() {
        model = new Model();
        view = new ConsoleView();
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        view.startGameMenu();
        setupGame();
        gameLoop();
//        view.printGameBoard(model.getGameBoardWithMoves(model.getPossibleMoves(Player.BLACK)));
    }

    private void gameLoop() {
        Player currentPlayer;
        while (model.gameNotFinished()) {
            currentPlayer = model.getCurrentPlayer();
            if (isPlayerTurn(currentPlayer)) {
                Move move = getPlayerMove(currentPlayer);
                model.placeDisc();
            }
        }

        view.gameOverMessage(model.getWinner());
    }

    private Move getPlayerMove(Player currentPlayer) {
        Set<Move> possibleMoves = model.getPossibleMoves(currentPlayer);
        Move move = null;
        String line;
        while (!possibleMoves.contains(move)) {
            view.playerMovePrompt(currentPlayer);
            line = scanner.nextLine();
            move = moveFromInput(line);
        }
        return move;
    }

    private void setupGame() {
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> {
                chooseColor();
                model.initGame();
            }
            case "2" -> {
                player = Player.PVP;
                model.initGame();
            }
            case "3" -> System.exit(0);
            default -> {
                view.invalidOptionMenu();
                view.chooseModeMenu();
                setupGame();
            }
        }
    }

    private void chooseColor() {
        view.chooseColorMenu();

        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> player = Player.BLACK;
            case "2" -> player = Player.WHITE;
            default -> {
                view.invalidOptionMenu();
                chooseColor();
            }
        }
    }

    private boolean isPlayerTurn(Player currentPlayer) {
        return player.equals(currentPlayer) || player.equals(Player.PVP);
    }

    private Move moveFromInput(String line) {
        line = line.toUpperCase();
        return inputMatchesFormat(line)
                ? new Move(line.charAt(0) - 'A', line.charAt(1) - '1')
                : null;
    }

    private boolean inputMatchesFormat(String line) {
        return line.matches("^[A-H][1-8]$");
    }
}
