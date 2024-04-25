package org.example.controllers;

import org.example.Models.Game;
import org.example.Models.GameState;
import org.example.Models.Player;
import org.example.exceptions.InvalidMoveException;

import java.util.List;

public class GameController {

    public static Game startGame(int dimension, List<Player> players){
        //TODO
        //Validate if 2 players have same symbol or not
        //If two players have same symbol, throw an exception.

        return new Game(dimension, players);
    }

    public void makeMove() throws InvalidMoveException {
        Game.makeMove();
    }

    public GameState checkGame(Game game){
        return game.getGameState();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void printBoard(Game game){
        game.printBoard();
    }


}
