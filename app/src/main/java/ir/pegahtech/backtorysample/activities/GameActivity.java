package ir.pegahtech.backtorysample.activities;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ir.pegahtech.backtorysample.R;
import ir.pegahtech.backtorysample.views.LeaderboardView;
import ir.pegahtech.backtorysample.views.PlayGameView;

/**
 * Created by SirGozal on 6/1/2016.
 */

@EActivity(R.layout.activity_game)
public class GameActivity extends Activity {

    @ViewById(R.id.buttonPlayGame)
    Button bPlayGame;

    @ViewById(R.id.buttonLeaderboardGame)
    Button bLeaderBoardGame;

    @Bean
    PlayGameView playGameView;

    @Bean
    LeaderboardView leaderboardView;

    @AfterViews
    void afterViews() {
        buttonPlayGame();
    }

    @Click
    public void buttonPlayGame() {
        playGameView.setVisibility(View.VISIBLE);
        leaderboardView.setVisibility(View.GONE);
        bPlayGame.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        bLeaderBoardGame.setBackgroundColor(ContextCompat.getColor(this, R.color.lightGray));
        bPlayGame.setTextColor(ContextCompat.getColor(this, R.color.white));
        bLeaderBoardGame.setTextColor(ContextCompat.getColor(this, R.color.titleGray));
    }

    @Click
    public void buttonLeaderboardGame() {
        playGameView.setVisibility(View.GONE);
        leaderboardView.setVisibility(View.VISIBLE);
        bLeaderBoardGame.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        bPlayGame.setBackgroundColor(ContextCompat.getColor(this, R.color.lightGray));
        bLeaderBoardGame.setTextColor(ContextCompat.getColor(this, R.color.white));
        bPlayGame.setTextColor(ContextCompat.getColor(this, R.color.titleGray));
        leaderboardView.getTop();
    }
}
