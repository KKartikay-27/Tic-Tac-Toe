package org.example;

import org.example.Models.*;
import org.example.controllers.GameController;
import org.example.exceptions.InvalidMoveException;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("Hello World!");
        Scanner scan = new Scanner(System.in);
        GameController gameController = new GameController();


        int dimension = 3;

        List<Player> players = List.of(
                new Player("Harsh", new Symbol('X'), PlayerType.HUMAN),
                new Bot("Scaler", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY)
        );

        Game game =gameController.startGame(dimension,players);

//        gameController.printBoard(game);


        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            //1.print the board
            gameController.printBoard(game);

            //2.Player's turn
            gameController.makeMove();
        }

        if(!gameController.checkGame(game).equals(GameState.ENDED)){
            game.setGameState(GameState.DRAW);
            System.out.println("Game DRAW");
        }
        else{
            gameController.printBoard(game);
            System.out.println("Player " + gameController.getWinner(game).getName() + " is the winner");
        }
    }
}