package com.ltolentino.tictactoe.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;
import com.ltolentino.tictactoe.model.Cell;
import com.ltolentino.tictactoe.model.Game;
import com.ltolentino.tictactoe.model.Player;

import static com.ltolentino.tictactoe.utilities.StringUtility.stringFromNumbers;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;

    private Game game;


    public void init(String player1, String player2) {

        game = new Game(player1, player2);

        cells = new ObservableArrayMap<>();
    }


    public void onClickedCellAt(int row, int column) {

        if (game.Cells[row][column] == null) {

            game.Cells[row][column] = new Cell(game.CurrentPlayer);

            cells.put(stringFromNumbers(row, column), game.CurrentPlayer.Value);

            if (game.hasGameEnded())

                game.reset();

            else
                game.SwitchPlayer();
        }
    }


    public LiveData<Player> getWinner() {

        return game.Winner;
    }
}
