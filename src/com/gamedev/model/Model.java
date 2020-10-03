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

    private void clearBoard() {
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
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

    public int[][] getBoard() {
        return board;
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
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != 0) continue;

//              search for opponent discs in square
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++) {
                        if ((row + i < 0 || row + i >= BOARD_SIZE)
                                || (col + j < 0 || col + j >= board[row].length)
                                || (i == 0 && j == 0)) continue;

                        if (board[row + i][col + j] == opponentDisc) {
                            int x = row + i;
                            int y = col + j;
//                          search for player discs in direction
                            while (true) {
                                x += i;
                                y += j;
                                if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE
                                        || board[x][y] == 0) break;
                                if (board[x][y] == playerDisc) {
                                    moves.add(new Move(row, col));
                                }
                            }
                        }
                    }
            }
        return moves;
    }

    public int[][] getGameBoardWithMoves(Set<Move> moves) {
        int[][] gameBoardWithMoves = board.clone();
        moves.forEach((move) -> gameBoardWithMoves[move.getRow()][move.getCol()] = 3);
        return gameBoardWithMoves;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean gameNotFinished() {
        return getPossibleMoves(Player.BLACK).size() + getPossibleMoves(Player.WHITE).size() > 0;
    }

    public Player getWinner() {
        return null;
    }
}
