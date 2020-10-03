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
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                if(board[row][col] == playerDisc) {
//                    System.out.println((row + 1 ) + " " + (col + 1));
                    if (col != board[row].length - 1)
                        checkMovesOnRight(row,col, moves, opponentDisc);
                    if (col != 0)
                        checkMovesOnLeft(row,col, moves, opponentDisc);
                    if (row != 0)
                        checkMovesOnUp(row,col, moves, opponentDisc);
                    if (row != board.length - 1)
                        checkMovesOnDown(row,col, moves, opponentDisc);
                    if (col != board[row].length - 1 && row != 0)
                        checkMovesOnRightUp(row,col, moves, opponentDisc);
                    if (col != board[row].length - 1 && row != board.length - 1)
                        checkMovesOnRightDown(row,col, moves, opponentDisc);
                    if (col != 0 && row != 0)
                        checkMovesOnLeftUp(row,col, moves, opponentDisc);
                    if (col != 0 && row != board.length - 1)
                        checkMovesOnLeftDown(row,col, moves, opponentDisc);

                }
            }
        }
        return moves;
    }

    private void checkMovesOnRight(int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = col + 1; i < board[col].length; i++) {
            if (board[row][i] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (board[row][i] == 0 && capturesOpponentDisc) {
                moves.add(new Move(row, i));
            }
            break;
        }
    }

    private void checkMovesOnLeft(int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = col - 1; i >= 0; i--) {
            if (board[row][i] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (board[row][i] == 0 && capturesOpponentDisc) {
                moves.add(new Move(row, i));
            }
            break;
        }
    }

    private void checkMovesOnUp(int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = row - 1; i >= 0; i--) {
            if (board[i][col] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (board[i][col] == 0 && capturesOpponentDisc) {
                moves.add(new Move(i, col));
            }
            break;
        }
    }

    private void checkMovesOnDown(int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = row + 1; i < board.length; i++) {
            if (board[i][col] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (board[i][col] == 0 && capturesOpponentDisc) {
                moves.add(new Move(i, col));
            }
            break;
        }
    }

    private void checkMovesOnRightUp(int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        row --; col ++;
        while ( row >= 0 && col < board[row].length){
            if (board[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                row --; col ++;
                continue;
            }
            if (board[row][col] == 0 && capturesOpponentDisc) {
                moves.add(new Move(row, col));
            }
            break;
        }
    }

    private void checkMovesOnRightDown (int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        row ++; col ++;
        while ( row < board.length && col < board[row].length){
            if (board[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                row ++; col ++;
                continue;
            }
            if (board[row][col] == 0 && capturesOpponentDisc) {
                moves.add(new Move(row, col));
                System.out.println((row + 1) + " " + (col + 1));
            }
            break;
        }
    }

    private void checkMovesOnLeftUp (int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        row --; col --;
        while ( row >= 0 && col >= 0){
            if (board[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                row --; col --;
                continue;
            }
            if (board[row][col] == 0 && capturesOpponentDisc) {
                moves.add(new Move(row, col));
            }
            break;
        }
    }

    private void checkMovesOnLeftDown (int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        row ++; col --;
        while ( row < board.length && col >= 0){
            if (board[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                row ++; col --;
                continue;
            }
            if (board[row][col] == 0 && capturesOpponentDisc) {
                moves.add(new Move(row, col));
            }
            break;
        }
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
