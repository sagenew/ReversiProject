package gamedev.controller;

import gamedev.player.impl.AIPlayer;
import gamedev.player.impl.UserPlayer;
import gamedev.model.ReversiGame;
import gamedev.model.entity.Move;
import gamedev.model.entity.Player;
import gamedev.player.strategy.minimax.MinimaxStrategy;

import java.util.Scanner;

public class Controller {
    Scanner scanner;
    ReversiGame reversiGame;
    gamedev.player.Player player1;
    gamedev.player.Player player2;

    private int moveCounter;

    public Controller() {
        scanner = new Scanner(System.in);
        reversiGame = new ReversiGame();
    }

    public void init() {
        initBlackHolePos();
        initPlayers();
        startGameLoop();
    }

    private void initBlackHolePos() {
        String blackHolePos = scanner.nextLine();
        Move blackHole = stringToMove(blackHolePos);
        reversiGame.setBlackHole(blackHole);
    }

    private void initPlayers() {
        Player player1Colour;
        Player player2Colour;
        String player1ColourString = scanner.nextLine();
        if (player1ColourString.equals("white")) {
            reversiGame.placeDisc(stringToMove(scanner.nextLine()));
            player1Colour = Player.WHITE;
            player2Colour = Player.BLACK;
        } else if (player1ColourString.equals("black")) {
            player1Colour = Player.BLACK;
            player2Colour = Player.WHITE;
        } else throw new RuntimeException();
        player1 = new AIPlayer(new MinimaxStrategy(), reversiGame, player1Colour);
        player2 = new UserPlayer(reversiGame, player2Colour);
    }

    private void startGameLoop() {
        Move nextMove;
        while (reversiGame.gameNotFinished()) {
            if (moveCounter % 2 == 0) {
                nextMove = player1.getNextMove();
                System.out.println(moveToString(nextMove));
            } else {
                nextMove = player2.getNextMove();
            }
            moveCounter++;
            reversiGame.placeDisc(nextMove);
        }
    }

    private Move stringToMove(String line) {
        line = line.toUpperCase();
        return new Move(line.charAt(1) - '1', line.charAt(0) - 'A');
    }

    private String moveToString(Move move) {
        if (move == null) return "pass";
        String s = "";
        char row = (char) move.getRow();
        row += '1';
        char col = (char) move.getCol();
        col += 'A';
        s = s.concat(String.valueOf(col)).concat(String.valueOf(row));
        return s;
    }
}
