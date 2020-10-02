package com.gamedev.controller;

import com.gamedev.model.Model;
import com.gamedev.view.ConsoleView;

import javax.swing.text.View;
import java.util.Map;

public class Controller {

    Model model;
    ConsoleView view;

    public Controller() {
        model = new Model();
        view = new ConsoleView();
    }

    public void startGame() {
        view.printGameBoard(model.getGameBoard());
        view.printGameBoard(model.getGameBoardWithMoves(model.getPossibleMoves(Model.Player.BLACK)));
    }
}
