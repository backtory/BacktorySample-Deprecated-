package ir.pegahtech.backtorysample.views;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ir.pegahtech.backtorysample.R;
import ir.pegahtech.backtorysample.activities.GameActivity;
import ir.pegahtech.backtorysample.managers.BacktoryConfig;
import ir.pegahtech.backtorysample.models.EventField;
import ir.pegahtech.backtorysample.models.EventRequest;
import ir.pegahtech.backtorysample.models.LoginResponse;
import ir.pegahtech.backtorysample.network.BacktoryErrorHandler;
import ir.pegahtech.backtorysample.network.LeaderboardServiceInterface;
import ir.pegahtech.backtorysample.utils.Utils;

/**
 * Created by SirGozal on 2/9/2016.
 */

@EBean
public class PlayGameView {
    private int score = 0;
    private CountDownTimer countDownTimer = new CountDownTimer(10000, 100) {

        public void onTick(long millisUntilFinished) {
            tvCountDown.setText(context.getString(R.string.game_time) + " " +
                    String.format("%.1f", (float) millisUntilFinished / 1000L));
        }

        public void onFinish() {
            bStart.setVisibility(View.VISIBLE);
            bClick.setVisibility(View.GONE);
            tvCountDown.setText(context.getString(R.string.game_time) + " 0.0");
            sendEvent(score);
        }
    };

    @RootContext
    Context context;

    @RootContext
    GameActivity gameActivity;

    @RestService
    LeaderboardServiceInterface leaderboardServiceInterface;

    @ViewById(R.id.buttonStartPlayGame)
    Button bStart;

    @ViewById(R.id.buttonClickPlayGame)
    Button bClick;

    @ViewById(R.id.textCountDownGame)
    TextView tvCountDown;

    @ViewById(R.id.textScoreGame)
    TextView tvScore;

    @ViewById(R.id.viewPlayGame)
    View vPlayGame;

    @ViewById(R.id.viewBoardPlayGame)
    RelativeLayout relativeLayout;

    @Bean
    BacktoryErrorHandler backtoryErrorHandler;

    @Bean
    Utils utils;

    @Bean
    LoadingDialog loadingDialog;

    @Bean
    SetScoreDialog setScoreDialog;

    @AfterViews
    void afterView() {
        setVisibility(View.VISIBLE);
        leaderboardServiceInterface.setRestErrorHandler(backtoryErrorHandler);
    }

    public void setVisibility(int visibility) {
        if (vPlayGame != null)
            vPlayGame.setVisibility(visibility);
    }

    @Click
    void buttonClickPlayGame() {
        tvScore.setText(context.getString(R.string.game_score) + " " + (++score));
        setNextPosition();
    }

    @Click
    void buttonStartPlayGame() {
        score = 0;
        tvScore.setText(context.getString(R.string.game_score) + " 0");
        countDownTimer.start();
        bStart.setVisibility(View.GONE);
        bClick.setVisibility(View.VISIBLE);
    }

    @Background
    void sendEvent(long value) {
        loadingDialog.show();
        try {
            EventRequest eventRequest = new EventRequest();
            eventRequest.setEventName(BacktoryConfig.EVENT_NAME);
            List<EventField> eventFieldList = new ArrayList<>();
            EventField eventField = new EventField();
            eventField.setFieldName(BacktoryConfig.EVENT_FIELD);
            eventField.setValue(value);
            eventFieldList.add(eventField);
            eventRequest.setFieldsAndValues(eventFieldList);
            ResponseEntity<LoginResponse> responseEntity = leaderboardServiceInterface
                    .sendEvent(eventRequest);
            setScoreDialog.build();
        } catch (HttpClientErrorException e) {
        }
        loadingDialog.hide();
    }

    void setNextPosition() {
        RelativeLayout.LayoutParams absParams =
                (RelativeLayout.LayoutParams) bClick.getLayoutParams();

        int width = relativeLayout.getWidth() - bClick.getWidth();
        int height = relativeLayout.getHeight() - bClick.getHeight();

        Random r = new Random();

        absParams.leftMargin = r.nextInt(width);
        absParams.topMargin = r.nextInt(height);
        bClick.setLayoutParams(absParams);
    }
}
