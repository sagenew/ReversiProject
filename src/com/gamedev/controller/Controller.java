package com.gamedev.controller;

import com.gamedev.model.Model;
import com.gamedev.model.Model.*;
import com.gamedev.view.ConsoleView;

import java.util.Scanner;

import static com.gamedev.controller.MessageUtils.*;

public class Controller {
    Model model;
    ConsoleView view;
    Scanner scanner;
    Player player;

    public Controller() {
        model = new Model();
        view = new ConsoleView();
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        startGameMenu();
        setupGame();
    }

    private void setupGame() {
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> {
                chooseColor();
                model.initGame();
            }
            case "2" -> {
                player = Player.PVP;
                model.initGame();
            }
            case "3" -> System.exit(0);
            default -> {
                invalidOptionMenu();
                chooseModeMenu();
                setupGame();
            }
        }
    }

    private void chooseColor() {
        chooseColorMenu();

        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> player = Player.BLACK;
            case "2" -> player = Player.WHITE;
            default -> {
                invalidOptionMenu();
                chooseColor();
            }
        }
    }
}
