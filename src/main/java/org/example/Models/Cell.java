package org.example.Models;

public class Cell {
    private int row;
    private int col;
    private Player player;
    private CellState cellState;

    public Cell(int row, int col, Player p) {
        this.row = row;
        this.col = col;
        this.player = p;
        this.cellState = CellState.EMPTY;
    }
}
