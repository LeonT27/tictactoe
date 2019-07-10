package com.ltolentino.tictactoe.model;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

public class Game {

    private static final String TAG = Game.class.getSimpleName();
    private  static final int BOARD_SIZE = 3;

    public Player Player1;
    public Player Player2;
    public Player CurrentPlayer;
    public Cell[][] Cells;
    public MutableLiveData<Player> Winner = new MutableLiveData<>();

    public Game(String playerOne, String playerTwo) {
        Cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        Player1 = new Player(playerOne, "X");
        Player2 = new Player(playerTwo, "O");
        CurrentPlayer = Player1;
    }

    public void SwitchPlayer() {
        CurrentPlayer = CurrentPlayer == Player1 ? Player2 : Player1;
    }

    public boolean hasGameEnded() {
        if (hasThreeSameHorizontalCells() || hasThreeSameVerticalCells() ||
                hasThreeSameDiagonalCells()) {
            Winner.setValue(CurrentPlayer);

            return true;
        }

        if (isBoardFull()) {
            Winner.setValue(null);

            return true;
        }

        return false;
    }

    public boolean hasThreeSameHorizontalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(Cells[i][0], Cells[i][1], Cells[i][2]))
                    return true;

            return false;
        }
        catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());

            return false;
        }
    }

    public boolean hasThreeSameVerticalCells() {
        try {
            for (int i = 0; i < BOARD_SIZE; i++)
                if (areEqual(Cells[0][i], Cells[1][i], Cells[2][i]))
                    return true;

            return false;
        }
        catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());

            return false;
        }
    }

    public boolean hasThreeSameDiagonalCells() {
        try {
            return areEqual(
                    Cells[0][0], Cells[1][1], Cells[2][2]) ||
                    areEqual(
                            Cells[0][2], Cells[1][1], Cells[2][0]);
        }
        catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());

            return false;
        }
    }

    public boolean isBoardFull() {
        for (Cell[] row : Cells)
            for (Cell cell : row)
                if (cell == null ||cell.isEmpty())
                    return false;

        return true;
    }
    /**
     * 2 cells are equal if:
     * - Both are none null
     * - Both have non null values
     * - both have equal values
     *
     *
     @param cells: Cells to check if are equal
     *
     @return boolean

     */

    private boolean areEqual(Cell... cells) {
        if (cells == null || cells.length == 0)
            return false;

        for (Cell cell : cells)
            if (cell == null || cell.Player.Value == null || cell.Player.Value.length() == 0)
                return false;

        Cell comparisonBase = cells[0];

        for (int i = 1; i < cells.length; i++)
            if (!comparisonBase.Player.Value.equals(cells[i].Player.Value))
                return false;

        return true;
    }

    public void reset() {
        Player1 = null;
        Player2 = null;
        CurrentPlayer = null;
        Cells = null;
    }
}
