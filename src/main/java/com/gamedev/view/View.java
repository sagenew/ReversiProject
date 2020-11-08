package com.gamedev.view;

import com.gamedev.model.entity.GameResult;
import com.gamedev.model.entity.Move;
import com.gamedev.model.entity.Player;

public interface View {
    void printGameBoard(int[][] gameBoard);

    void startGameMenu();

    void chooseModeMenu();

    void chooseColorMenu();

    void invalidOptionMenu();

    void invalidMove();

    void playerMovePrompt(Player player);

    void gameOverMessage(GameResult result);

    void hintsOptionMessage(boolean showHints);

    void lastMoveMessage(Move move);

    void continueGameOrAbort();

    void gameScoreMessage(int[] discsCount);

    void clearScreen();
}
