package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> m;
    public GameState getGameState;
    private Player winner;
    private int nextPlayerMoveIndex;

    public Game(int dimension, List<Player> players){
        this.board = new Board(dimension);
        this.players = players;
        this.m = new ArrayList<>();
        this.getGameState = GameState.IN_PROGRESS;
        this.winner = null;
        this.nextPlayerMoveIndex = 0;
    }
}
