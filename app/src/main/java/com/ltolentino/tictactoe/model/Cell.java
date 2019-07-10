package com.ltolentino.tictactoe.model;

public class Cell {

    public Player Player;

    public Cell(Player player) {
        Player = player;
    }

    public boolean isEmpty() {
        return Player == null;
    }
}
