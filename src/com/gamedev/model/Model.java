package com.gamedev.model;

import com.gamedev.model.entity.Move;
import com.gamedev.model.entity.Player;

import java.util.*;

public class Model {
    private int [][] board;
    private Player currentPlayer;

    public void initGame() {
        board = new int[8][8];
        placeStartDiscs();
        currentPlayer = Player.BLACK;

    }

    private void clearBoard() {
        for (int[] row: board) {
            Arrays.fill(row, 0);
        }
    }
    private void placeStartDiscs() {
        board[3][3] = 2;
        board[3][4] = 1;
        board[4][3] = 1;
        board[4][4] = 2;
    }

    public int[][] getBoard() {
        return board;
    }

    public Set<Move> getPossibleMoves(Player player) {
        Set<Move> moves = new HashSet<>();
        int playerDisc = (player == Player.BLACK) ? 2 : 1;
        int opponentDisc = (player == Player.BLACK) ? 1 : 2;
        for(int row = 0; row < board.length; row++)
            for(int col = 0; col < board[row].length; col++) {
                if(board[row][col] != 0) continue;

//              search for opponent discs in square
                for(int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++) {
                        if((row + i < 0 || row + i >= board.length)
                        || (col + j < 0 || col + j >= board[row].length)
                        || (i == 0 && j == 0)) continue;

                        if(board[row + i][col + j] == opponentDisc) {
                            int x = row + i; int y = col + j;
//                          search for player discs in direction
                            while (true) {
                                x += i; y += j;
                                if(x < 0 || x >= board.length || y < 0 || y >= board.length
                                        || board[x][y] == 0) break;
                                if(board[x][y] == playerDisc) {
                                    moves.add(new Move(row,col));
                                }
                            }
                        }
                    }
            }
        return moves;
    }

    public int [][] getGameBoardWithMoves(Set<Move> moves) {
        int [][] gameBoardWithMoves = board.clone();
        moves.forEach((move) -> gameBoardWithMoves[move.getRow()][move.getCol()] = 3);
        return gameBoardWithMoves;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean gameNotFinished() {
        return getPossibleMoves(Player.BLACK).size() + getPossibleMoves(Player.WHITE).size() > 0;
    }

    public void placeDisc() {
    }

    public Player getWinner() {
        return null;
    }
}
