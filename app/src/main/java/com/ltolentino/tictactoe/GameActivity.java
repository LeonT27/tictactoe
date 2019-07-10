package com.ltolentino.tictactoe;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ltolentino.tictactoe.databinding.ActivityGameBinding;
import com.ltolentino.tictactoe.model.Player;
import com.ltolentino.tictactoe.viewModel.GameViewModel;

import static com.ltolentino.tictactoe.utilities.StringUtility.isNullOrEmpty;

public class GameActivity extends AppCompatActivity {

    private static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";

    private static final String GAME_END_DIALOG_TAG = "game_end_dialog_tag";

    private static final String NO_WINNER = "No one";

    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayers();
    }

    public void promptForPlayers() {
        GameBeginDialog dialog = GameBeginDialog.
                newInstance(this);
        dialog.show(getSupportFragmentManager(),
                GAME_BEGIN_DIALOG_TAG);
    }

    public void onPlayersSet(String player1, String player2) {
        initDataBinding(player1, player2);
    }

    private void initDataBinding(String player1, String player2) {
        ActivityGameBinding activityGameBinding = DataBindingUtil.
                setContentView(this, R.layout.activity_game);

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);

        gameViewModel.init(player1, player2);
        activityGameBinding.setGameViewModel(
                gameViewModel);
        setUpOnGameEndListener();
    }


    private void setUpOnGameEndListener() {

        gameViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    public void onGameWinnerChanged(Player winner) {
        String winnerName = winner ==
                null || isNullOrEmpty(winner.Name) ? NO_WINNER : winner.Name;
        GameEndDialog dialog = GameEndDialog.
                newInstance(this, winnerName);
        dialog.show(getSupportFragmentManager(),
                GAME_END_DIALOG_TAG);
    }
}
