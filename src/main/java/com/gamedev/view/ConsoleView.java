package com.gamedev.view;

import com.gamedev.model.entity.Move;
import com.gamedev.model.entity.Player;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;

public class ConsoleView {

    public void printGameBoard(int[][] gameBoard) {
        System.out.println("    A    B    C    D    E    F    G    H    ");
        printHorizontalLine();
        for (int row = 0; row < gameBoard.length; row++) {
            System.out.print(row + 1);
            System.out.print(" | ");
            for (int col = 0; col < gameBoard[row].length; col++) {
                switch (gameBoard[row][col]) {
                    case 0: System.out.printf("   %c ", '|');
                            break;
                    case 1: System.out.printf("%s %c ", getWhiteDisc(), '|');
                            break;
                    case 2: System.out.printf("%s %c ", getBlackDisc(), '|');
                            break;
                    case 3: System.out.printf("%c %c ", '◯', '|');
                            break;
                }
            }
            System.out.print("\n");
            printHorizontalLine();
        }
    }

    private String getBlackDisc() {
        if (System.getProperty("os.name").equals("Linux")) return "🔴";
        else return " x";
    }

    private String getWhiteDisc() {
        if (System.getProperty("os.name").equals("Linux")) return "🔵";
        else return " o";
    }

    public void printHorizontalLine() {
        System.out.println("  +----+----+----+----+----+----+----+----+");
    }

    public void startGameMenu() {
        System.out.println("Welcome to Othello game!");
        chooseModeMenu();
    }

    public void chooseModeMenu() {
        System.out.println("Please choose the mode you want to play:");
        System.out.println("1 - Play vs Computer");
        System.out.println("2 - HotSeat (play vs another player)");
        System.out.println("3 - Turn on/off possible moves hint (Disabled by default)");
        System.out.println("4 - Exit");
    }

    public void chooseColorMenu() {
        System.out.println("Choose color:");
        System.out.println("1 - Black");
        System.out.println("2 - White");
    }

    public void invalidOptionMenu() {
        System.out.println("Invalid option chosen! Please choose again.");
    }

    public void playerMovePrompt(Player player) {
        System.out.print("Enter move for " + player.toString() + " player: ");
    }

    public void gameOverMessage(Player player) {
        if (player == Player.TIE) {
            System.out.println("Tie! No player wins!");
        } else {
            System.out.println(player.toString() + " player wins!");
        }
    }

    public void hintsOptionMessage(boolean showHints) {
        if (showHints) {
            System.out.println("Hints enabled!");
        } else {
            System.out.println("Hints disabled!");
        }
    }

    public void computerMoveMessage(Move move) {
        if (move == null) {
            System.out.println("[COMPUTER] Waiting for your move!");
        } else {
            System.out.println("[COMPUTER] My last move was: " + (char) ('A' + move.getCol()) + (char) ('1' + move.getRow()));
        }
    }

    public void continueGameOrAbort() {
        System.out.println("If you want to continue playing press 1, exit - 2.");
    }

    public void gameScoreMessage(int[] discsCount) {
        System.out.println("[DISCS SCORE] Black " + discsCount[0] + ":" + discsCount[1] + " White");
    }

    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
