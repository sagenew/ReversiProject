package gamedev.player.impl;

import gamedev.model.ReversiGame;
import gamedev.model.entity.Move;
import gamedev.model.entity.Player;

import java.util.Scanner;

public class UserPlayer implements gamedev.player.Player {
    ReversiGame reversiGame;
    Player player;
    Scanner scanner;

    public UserPlayer(ReversiGame reversiGame, Player player) {
        this.reversiGame = reversiGame;
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Move getNextMove() {
        String moveString = "";
        while (moveString.equals("")) {
            moveString = scanner.nextLine();
        }
        if(moveString.equals("pass")) return null;
        return new Move(moveString.charAt(1) - '1', moveString.charAt(0) - 'A');
    }

    @Override
    public Player getPlayerColour() {
        return player;
    }
}
