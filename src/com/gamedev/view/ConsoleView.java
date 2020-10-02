package com.gamedev.view;

public class ConsoleView {
    public void printGameBoard(int[][] gameBoard) {
        System.out.println("    A    B    C    D    E    F    G    H    ");
        printHorizontalLine();
        for (int row = 0; row < gameBoard.length; row++) {
            System.out.print(row + 1);
            System.out.print(" | ");
            for (int col = 0; col < gameBoard[row].length ; col++) {
                switch (gameBoard[row][col]) {
                    case 0 -> System.out.print("   | ");
                    case 1 -> System.out.print("🔵 | ");
                    case 2 -> System.out.print("🔴 | "); //🔴⚫🔵⚪
                    case 3 -> System.out.print("◯ | ");
                }
            }
            System.out.print("\n");
            printHorizontalLine();
        }
    }
    public void printHorizontalLine() {
            System.out.println("  +----+----+----+----+----+----+----+----+");
    }

    public void printWelcomingMessage() {

    }
}
