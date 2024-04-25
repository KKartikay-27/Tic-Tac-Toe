package org.example.Models;

import org.example.exceptions.InvalidMoveException;
import org.example.strategies.WinningAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Board board;
    private static List<Player> players;
    private static List<Move> moves;
    public static GameState gameState;
    private static Player winner;
    private static int nextPlayerMoveIndex;
    private static WinningAlgorithm winningAlgorithm;

    public Game(int dimension, List<Player> players) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winner = null;
        this.nextPlayerMoveIndex = 0;
        this.winningAlgorithm = new WinningAlgorithm();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public void printBoard(){
        this.board.printBoard();
    }

    private static boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int column = move.getCell().getCol();

        if(row < 0 || row >= board.getSize()){
            return false;
        }
        if(column < 0 || column >= board.getSize()){
            return false;
        }
        return board.getBoard().get(row).get(column).getCellState().equals(CellState.EMPTY);
    }

    public static void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerMoveIndex);

        System.out.println("It is " + currentPlayer.getName() + "'s turn");

        //Move that current player wants to make
        Move move = currentPlayer.makeMove(board);

        //Game will validate the move before executing
        if(!validateMove(move)){
            //throw an exception
            throw new InvalidMoveException("Invalid Move made by " + currentPlayer.getName());
        }

        int row = move.getCell().getRow();
        int column = move.getCell().getCol();

        Cell cellToChange = board.getBoard().get(row).get(column);
        cellToChange.setPlayer(currentPlayer);
        cellToChange.setCellState(CellState.FILLED);

        Move finalMove = new Move(cellToChange,currentPlayer);
        moves.add(finalMove);

        nextPlayerMoveIndex = (nextPlayerMoveIndex + 1) % players.size();

        //Check if the current move is the winning move or not
        if(winningAlgorithm.checkWinner(board,finalMove)){
            gameState = gameState.ENDED;
            winner = currentPlayer;
        }

    }
}
