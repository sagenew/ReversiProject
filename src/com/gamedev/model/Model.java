package com.gamedev.model;

import java.util.*;

public class Model {
    private int [][] gameBoard;

    public enum Player{WHITE, BLACK}

    class Move {
        int row;
        int col;

        public Move(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

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
        gameBoard[0][0] = 2;
        gameBoard[0][1] = 1;
        gameBoard[1][1] = 1;
        gameBoard[1][0] = 1;
        gameBoard[2][2] = 2;
        gameBoard[2][3] = 1;
        gameBoard[7][7] = 2;
        gameBoard[7][0] = 2;
        gameBoard[0][7] = 2;

    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public Set<Move> getPossibleMoves(Player player) {
        Set<Move> moves = new HashSet<>();
        int playerDisc = (player == Player.BLACK) ? 2 : 1;
        int opponentDisc = (player == Player.BLACK) ? 1 : 2;
        for(int row = 0; row < gameBoard.length; row++) {
            for(int col = 0; col < gameBoard[row].length; col++) {
                if(gameBoard[row][col] == playerDisc) {
//                    System.out.println((row + 1 ) + " " + (col + 1));
                    if (col != gameBoard[row].length - 1)
                    checkMovesOnRight(row,col, moves, opponentDisc);
                    if (col != 0)
                    checkMovesOnLeft(row,col, moves, opponentDisc);
                    if (row != 0)
                    checkMovesOnUp(row,col, moves, opponentDisc);
                    if (row != gameBoard.length - 1)
                    checkMovesOnDown(row,col, moves, opponentDisc);
                    if (col != gameBoard[row].length - 1 && row != 0)
                    checkMovesOnRightUp(row,col, moves, opponentDisc);
                    if (col != gameBoard[row].length - 1 && row != gameBoard.length - 1)
                    checkMovesOnRightDown(row,col, moves, opponentDisc);
                    if (col != 0 && row != 0)
                    checkMovesOnLeftUp(row,col, moves, opponentDisc);
                    if (col != 0 && row != gameBoard.length - 1)
                    checkMovesOnLeftDown(row,col, moves, opponentDisc);

                }
            }
        }
        return moves;
    }

    private void checkMovesOnRight(int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = col + 1; i < gameBoard[col].length; i++) {
            if (gameBoard[row][i] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[row][i] == 0 && capturesOpponentDisc) {
                moves.add(new Move(row, i));
            }
            break;
        }
    }

    private void checkMovesOnLeft(int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = col - 1; i >= 0; i--) {
            if (gameBoard[row][i] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[row][i] == 0 && capturesOpponentDisc) {
                moves.add(new Move(row, i));
            }
            break;
        }
    }

    private void checkMovesOnUp(int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = row - 1; i >= 0; i--) {
            if (gameBoard[i][col] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[i][col] == 0 && capturesOpponentDisc) {
                moves.add(new Move(i, col));
            }
            break;
        }
    }

    private void checkMovesOnDown(int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        for(int i = row + 1; i < gameBoard.length; i++) {
            if (gameBoard[i][col] == opponentDisc) {
                capturesOpponentDisc = true;
                continue;
            }
            if (gameBoard[i][col] == 0 && capturesOpponentDisc) {
                moves.add(new Move(i, col));
            }
            break;
        }
    }

    private void checkMovesOnRightUp(int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        row --; col ++;
        while ( row >= 0 && col < gameBoard[row].length){
            if (gameBoard[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                row --; col ++;
                continue;
            }
            if (gameBoard[row][col] == 0 && capturesOpponentDisc) {
                moves.add(new Move(row, col));
            }
            break;
        }
    }

    private void checkMovesOnRightDown (int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        row ++; col ++;
        while ( row < gameBoard.length && col < gameBoard[row].length){
            if (gameBoard[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                row ++; col ++;
                continue;
            }
            if (gameBoard[row][col] == 0 && capturesOpponentDisc) {
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
            if (gameBoard[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                row --; col --;
                continue;
            }
            if (gameBoard[row][col] == 0 && capturesOpponentDisc) {
                moves.add(new Move(row, col));
            }
            break;
        }
    }

    private void checkMovesOnLeftDown (int row, int col, Set<Move> moves, int opponentDisc) {
        boolean capturesOpponentDisc = false;
        row ++; col --;
        while ( row < gameBoard.length && col >= 0){
            if (gameBoard[row][col] == opponentDisc) {
                capturesOpponentDisc = true;
                row ++; col --;
                continue;
            }
            if (gameBoard[row][col] == 0 && capturesOpponentDisc) {
                moves.add(new Move(row, col));
            }
            break;
        }
    }

    public int [][] getGameBoardWithMoves(Set<Move> moves) {
        int [][] gameBoardWithMoves = gameBoard.clone();
        moves.forEach((move) -> gameBoardWithMoves[move.row][move.col] = 3);
        return gameBoardWithMoves;
    }

}
