package com.gamedev.controller;

import com.gamedev.model.entity.Player;

public class MessageUtils {
    private MessageUtils() {}

    static void startGameMenu() {
        System.out.println("Welcome to Othello game!");
        chooseModeMenu();
    }

    static void chooseModeMenu() {
        System.out.println("Please choose the mode you want to play:");
        System.out.println("1 - Play vs Computer");
        System.out.println("2 - HotSeat (play vs another player)");
        System.out.println("3 - Exit");
    }

    static void chooseColorMenu() {
        System.out.println("Choose color:");
        System.out.println("1 - Black");
        System.out.println("2 - White");
    }

    static void invalidOptionMenu() {
        System.out.println("Invalid option chosen! Please choose again.");
    }

    static void playerMovePrompt(Player player) {
        System.out.print("Enter move for " + player.toString() + " player: ");
    }

    static void gameOverMessage(Player player) {
        System.out.println(player.toString() + " wins!");
    }
}
