package com.gamedev.model;

import com.gamedev.model.entity.Move;
import com.gamedev.model.entity.Player;

import java.util.*;

public class Model {
    private int[][] board;
    private Player currentPlayer;
    private final int BOARD_SIZE = 8;

    public void initGame() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = Player.BLACK;
        placeStartDiscs();
    }

    private void placeStartDiscs() {
        board[3][3] = 2;
        board[3][4] = 1;
        board[4][3] = 1;
        board[4][4] = 2;
    }

    public void placeDisc(Move move) {
        int playerDisc = currentPlayer == Player.BLACK ? 2 : 1;
        int opponentDisc = currentPlayer == Player.BLACK ? 1 : 2;
        board[move.getRow()][move.getCol()] = playerDisc;

        int[] directionsX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] directionsY = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < directionsX.length; i++) {
            flipDiscsInDirection(playerDisc, opponentDisc, move, directionsX[i], directionsY[i]);
        }

        checkGameState();
    }

    private void checkGameState() {
        if (getPossibleMoves(Player.BLACK).size() + getPossibleMoves(Player.WHITE).size() == 0) {
            return;
        }

        if (getPossibleMoves(Player.BLACK).size() > 0 && getPossibleMoves(Player.WHITE).size() == 0) {
            currentPlayer = Player.BLACK;
            return;
        }

        if (getPossibleMoves(Player.BLACK).size() == 0 && getPossibleMoves(Player.WHITE).size() > 0) {
            currentPlayer = Player.WHITE;
            return;
        }

        passTurn();
    }

    private void flipDiscsInDirection(int player, int opponent, Move move, int directionX, int directionY) {
        int i = move.getRow() + directionX;
        int j = move.getCol() + directionY;

        Set<Move> toFlip = new HashSet<>();

        while (inBounds(i, j)) {
            if (board[i][j] == opponent) {
                toFlip.add(new Move(i, j));
            } else if (board[i][j] == player) {
                break;
            } else {
                toFlip.clear();
                break;
            }
            i = i + directionX;
            j = j + directionY;
        }

        if (!inBounds(i, j)) {
            toFlip.clear();
        }

        for (Move markedMove : toFlip) {
            board[markedMove.getRow()][markedMove.getCol()] = player;
        }
    }

    private boolean inBounds(int i, int j) {
        return ((i >= 0) && (i < 8) && (j >= 0) && (j < 8));
    }

    private void passTurn() {
        currentPlayer = currentPlayer == Player.BLACK
                ? Player.WHITE
                : Player.BLACK;
    }

    public boolean moveNotValid(Move move) {
        if (move == null) return true;

        for (Move moveToCheck : getPossibleMoves(currentPlayer)) {
            if (moveToCheck.getCol() == move.getCol()
                    && moveToCheck.getRow() == move.getRow()) return false;
        }

        return true;
    }

    public Set<Move> getPossibleMoves(Player player) {
        Set<Move> moves = new HashSet<>();
        int playerDisc = (player == Player.BLACK) ? 2 : 1;
        int opponentDisc = (player == Player.BLACK) ? 1 : 2;
        for (int row = 0; row < BOARD_SIZE; row++)
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] != 0) continue;

                int[] directionsX = {-1, -1, -1, 0, 0, 1, 1, 1};
                int[] directionsY = {-1, 0, 1, -1, 1, -1, 0, 1};
                for (int i = 0; i < directionsX.length; i++) {
                    if ((hasOpponentDiscInDirection(row + directionsX[i], col + directionsY[i], opponentDisc))
                            && hasPlayerDiscInDirection(row, col, directionsX[i], directionsY[i], playerDisc))
                        moves.add(new Move(row, col));

                }
            }
        return moves;
    }

    private boolean hasOpponentDiscInDirection(int row, int col, int opponentDisc) {
        if ((row < 0 || row >= BOARD_SIZE) || (col < 0 || col >= BOARD_SIZE)) return false;
        return board[row][col] == opponentDisc;
    }

    private boolean hasPlayerDiscInDirection(int row, int col, int directionX, int directionY, int playerDisc) {
        int x = row + directionX;
        int y = col + directionY;
        while (true) {
            x += directionX;
            y += directionY;
            if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE || board[x][y] == 0) break;
            if (board[x][y] == playerDisc) return true;
        }
        return false;
    }


    public int[][] getGameBoard(boolean showHints) {
        if (!showHints) return board;

        int[][] boardCopy = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);;
        Set<Move> moves = getPossibleMoves(currentPlayer);
        moves.forEach((move) -> boardCopy[move.getRow()][move.getCol()] = 3);
        return boardCopy;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean gameNotFinished() {
        return getPossibleMoves(Player.BLACK).size() + getPossibleMoves(Player.WHITE).size() > 0;
    }

    public Player getWinner() {
        int count = 0;

        for (int[] row : board) {
            for (int element : row) {
                if (element == 2) {
                    count++;
                } else if (element == 1) {
                    count--;
                }
            }
        }

        return count == 0
                ? Player.TIE
                : count > 0
                ? Player.BLACK
                : Player.WHITE;
    }

    public int[] getDiscsCount() {
        int[] discCount = {0, 0};

        for (int[] row : board) {
            for (int element : row) {
                if (element == 2) {
                    discCount[0]++;
                } else if (element == 1) {
                    discCount[1]++;
                }
            }
        }

        return discCount;
    }
}
