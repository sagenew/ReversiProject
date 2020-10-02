package com.gamedev.model;

import java.util.*;

public class Model {
    private int [][] gameBoard;

    public enum Player{WHITE, BLACK}

    public Model() {
        gameBoard = new int[8][8];
        clearTable();
        placeStartGameDiscs();
    }
    public void clearTable() {
        for (int[] row: gameBoard) {
            Arrays.fill(row, 0);
        }
    }
    public void placeStartGameDiscs() {
        gameBoard[3][3] = 2;
        gameBoard[3][4] = 1;
        gameBoard[4][3] = 1;
        gameBoard[4][4] = 2;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public Map<Integer, Integer> getPossibleMoves(Player player) {
        Map<Integer, Integer> moves = new HashMap<>();
        int playerDisc = (player == Player.BLACK) ? 2 : 1;
        int opponentDisc = (player == Player.BLACK) ? 1 : 2;
        for(int row = 0; row < gameBoard.length; row++) {
            for(int col = 0; col < gameBoard[row].length; col++) {
                if(gameBoard[row][col] == playerDisc) {
                    System.out.println((row + 1 ) + " " + (col + 1));
//                    if (col != gameBoard[row].length - 1)
                    checkMovesOnRight(row,col, moves, opponentDisc);
//                    if (col != 0)
                    checkMovesOnLeft(row,col, moves, opponentDisc);
                    checkMovesOnUp(row,col, moves, opponentDisc);
                    checkMovesOnDown(row,col, moves, opponentDisc);
                    checkMovesOnRightUp(row,col, moves, opponentDisc);
                    checkMovesOnRightDown(row,col, moves, opponentDisc);
                    checkMovesOnLeftUp(row,col, moves, opponentDisc);
                    checkMovesOnLeftDown(row,col, moves, opponentDisc);

                }
            }
        }
        return moves;
    }

    private void checkMovesOnRight(int row, int col, Map<Integer, Integer> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = col + 1; i < gameBoard[col].length; i++) {
            if (gameBoard[row][i] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[row][i] == 0 && capturesOpponentDisc) {
                moves.put(row, i);
            }
            break;
        }
    }

    private void checkMovesOnLeft(int row, int col, Map<Integer, Integer> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = col - 1; i >= 0; i--) {
            if (gameBoard[row][i] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[row][i] == 0 && capturesOpponentDisc) {
                moves.put(row, i);
            }
            break;
        }
    }

    private void checkMovesOnUp(int row, int col, Map<Integer, Integer> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = row - 1; i >= 0; i--) {
            if (gameBoard[i][col] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[i][col] == 0 && capturesOpponentDisc) {
                moves.put(i, col);
            }
            break;
        }
    }

    private void checkMovesOnDown(int row, int col, Map<Integer, Integer> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = row + 1; i < gameBoard.length; i++) {
            if (gameBoard[i][col] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[i][col] == 0 && capturesOpponentDisc) {
                moves.put(i, col);
            }
            break;
        }
    }

    private void checkMovesOnRightUp(int row, int col, Map<Integer, Integer> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        row --; col ++;
        while ( row >= 0 && col < gameBoard[row].length){
            if (gameBoard[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[row][col] == 0 && capturesOpponentDisc) {
                moves.put(row, col);
            }
            row --; col ++;
        }
    }

    private void checkMovesOnRightDown (int row, int col, Map<Integer, Integer> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        row ++; col ++;
        while ( row < gameBoard.length && col < gameBoard[row].length){
            if (gameBoard[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[row][col] == 0 && capturesOpponentDisc) {
                moves.put(row, col);
            }
            row ++; col ++;
        }
    }

    private void checkMovesOnLeftUp (int row, int col, Map<Integer, Integer> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        row --; col --;
        while ( row >= 0 && col >= 0){
            if (gameBoard[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[row][col] == 0 && capturesOpponentDisc) {
                moves.put(row, col);
            }
            row --; col --;
        }
    }

    private void checkMovesOnLeftDown (int row, int col, Map<Integer, Integer> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        row ++; col --;
        while ( row < gameBoard.length && col >= 0){
            if (gameBoard[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[row][col] == 0 && capturesOpponentDisc) {
                moves.put(row, col);
            }
            row ++; col --;
        }
    }

    public int [][] getGameBoardWithMoves(Map<Integer, Integer> moves) {
        int [][] gameBoardWithMoves = gameBoard.clone();
        moves.forEach((row,col) -> gameBoardWithMoves[row][col] = 3);
        return gameBoardWithMoves;
    }

}
