package ir.pegahtech.backtorysample.views;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import ir.pegahtech.backtorysample.R;
import ir.pegahtech.backtorysample.activities.GameActivity;
import ir.pegahtech.backtorysample.managers.BacktoryConfig;
import ir.pegahtech.backtorysample.models.GetTopLeaderboardResponse;
import ir.pegahtech.backtorysample.network.BacktoryErrorHandler;
import ir.pegahtech.backtorysample.network.LeaderboardServiceInterface;
import ir.pegahtech.backtorysample.utils.Utils;
import ir.pegahtech.backtorysample.views.adapters.LeaderboardAdapter;

/**
 * Created by SirGozal on 2/9/2016.
 */

@EBean
public class LeaderboardView {

    @Bean
    LeaderboardAdapter leaderboardAdapter;

    @RootContext
    Context context;

    @RootContext
    GameActivity gameActivity;

    @RestService
    LeaderboardServiceInterface leaderboardServiceInterface;

    @ViewById(R.id.buttonLogin)
    Button bLogin;

    @ViewById(R.id.editUsernameLogin)
    EditText etPhoneNumber;

    @ViewById(R.id.editPasswordLogin)
    EditText etPassword;

    @ViewById(R.id.viewLeaderboard)
    View vLeaderboard;

    @Bean
    BacktoryErrorHandler backtoryErrorHandler;

    @Bean
    Utils utils;

    @Bean
    LoadingDialog loadingDialog;

    @ViewById(R.id.listLeaderboard)
    ListView lvLeaderboard;

    @AfterViews
    void afterView() {
        setVisibility(View.GONE);
        leaderboardServiceInterface.setRestErrorHandler(backtoryErrorHandler);
    }

    public void setVisibility(int visibility) {
        if (vLeaderboard != null)
            vLeaderboard.setVisibility(visibility);
    }

    @Background
    public void getTop() {
        loadingDialog.show();
        try {
            ResponseEntity<GetTopLeaderboardResponse> responseEntity = leaderboardServiceInterface.
                    getTop(BacktoryConfig.LEADERBOARD_ID, 10);
            setLeaderboard(responseEntity.getBody());
        } catch (HttpClientErrorException e) {
        }
        loadingDialog.hide();
    }

    @UiThread
    void setLeaderboard(GetTopLeaderboardResponse getTopLeaderboardResponse) {
        leaderboardAdapter.setUserProfileList(getTopLeaderboardResponse.getUsersProfile());
        lvLeaderboard.setAdapter(leaderboardAdapter);
    }
}
