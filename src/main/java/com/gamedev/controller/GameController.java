package com.gamedev.controller;

import com.gamedev.controller.impl.AIController;
import com.gamedev.controller.impl.HumanController;
import com.gamedev.model.ReversiGame;
import com.gamedev.model.entity.GameMode;
import com.gamedev.model.entity.Move;
import com.gamedev.model.entity.Player;
import com.gamedev.view.ConsoleView;
import com.gamedev.view.View;

import java.util.Scanner;

public class GameController {
    private ReversiGame reversiGame;
    private View view;
    private Scanner scanner;
    private boolean showHints;
    private GameMode gameMode;

    private AbstractPlayerController playerWhite;
    private AbstractPlayerController playerBlack;

    public GameController() {
        reversiGame = new ReversiGame();
        view = new ConsoleView();
        scanner = new Scanner(System.in);
        gameMode = GameMode.SINGLE;
    }

    public void startGame() {
        view.startGameMenu();
        setupGame();
        for (;;) {
            gameLoop();
            gameOverMenu();
        }
    }

    private void gameLoop() {
        Player currentPlayer;
        Move lastMove = null;
        while (reversiGame.gameNotFinished()) {
            currentPlayer = reversiGame.getCurrentPlayer();

            view.clearScreen();
            view.printGameBoard(reversiGame.getGameBoard(showHints));
            view.gameScoreMessage(reversiGame.getDiscsCount());
            if (gameMode == GameMode.SINGLE) view.lastMoveMessage(lastMove);

            lastMove = currentPlayer == Player.BLACK
                    ? playerBlack.makeTurn()
                    : playerWhite.makeTurn();
        }
        view.gameOverMessage(reversiGame.getWinner());
    }

    private void setupGame() {
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                chooseColor();
                reversiGame.initGame();
                break;
            case "2":
                playerWhite = new HumanController(Player.WHITE, view, reversiGame);
                playerBlack = new HumanController(Player.BLACK, view, reversiGame);
                gameMode = GameMode.PVP;
                reversiGame.initGame();
                break;
            case "3":
                showHints = !showHints;
                view.hintsOptionMessage(showHints);
                view.chooseModeMenu();
                setupGame();
                break;
            case "4":
                System.exit(0);
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
                playerBlack = new HumanController(Player.BLACK, view, reversiGame);
                playerWhite = new AIController(reversiGame);
                break;
            case "2":
                playerBlack = new AIController(reversiGame);
                playerWhite = new HumanController(Player.WHITE, view, reversiGame);
                break;
            default:
                view.invalidOptionMenu();
                chooseColor();
                break;
        }
    }
}
