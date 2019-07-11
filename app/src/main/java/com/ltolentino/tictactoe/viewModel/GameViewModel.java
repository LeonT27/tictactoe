package com.ltolentino.tictactoe.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.res.Resources;
import android.databinding.ObservableArrayMap;
import android.graphics.drawable.Drawable;

import com.ltolentino.tictactoe.R;
import com.ltolentino.tictactoe.model.Cell;
import com.ltolentino.tictactoe.model.Game;
import com.ltolentino.tictactoe.model.Player;

import static com.ltolentino.tictactoe.utilities.StringUtility.stringFromNumbers;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, Drawable> cells;

    private Game game;

    public Context Context;


    public void init(String player1, String player2, Context context) {

        game = new Game(player1, player2);

        cells = new ObservableArrayMap<>();

        Context = context;
    }


    public void onClickedCellAt(int row, int column) {

        if (game.Cells[row][column] == null) {

            game.Cells[row][column] = new Cell(game.CurrentPlayer);

            cells.put(stringFromNumbers(row, column), getIcon());

            if (game.hasGameEnded())

                game.reset();

            else
                game.SwitchPlayer();
        }
    }


    public LiveData<Player> getWinner() {

        return game.Winner;
    }

    public Drawable getIcon() {
        Resources res = Context.getResources();
        if(game.CurrentPlayer.Value.equals("X")) {
            return res.getDrawable(R.drawable.cross);
        }
        return res.getDrawable(R.drawable.circle);
    }
}
