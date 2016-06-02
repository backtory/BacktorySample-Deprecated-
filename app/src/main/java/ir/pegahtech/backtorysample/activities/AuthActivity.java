package ir.pegahtech.backtorysample.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import ir.pegahtech.backtorysample.R;
import ir.pegahtech.backtorysample.managers.BacktoryConfig;
import ir.pegahtech.backtorysample.utils.Utils;
import ir.pegahtech.backtorysample.views.LoginView;
import ir.pegahtech.backtorysample.views.RegisterView;

/**
 * Created by SirGozal on 2/9/2016.
 */

@EActivity(R.layout.activity_auth)
public class AuthActivity extends Activity {

    @ViewById(R.id.buttonViewLoginAuth)
    Button bViewLogin;

    @ViewById(R.id.buttonViewRegisterAuth)
    Button bViewRegister;

    @Bean
    LoginView loginView;

    @Bean
    RegisterView registerView;

    @Bean
    Utils utils;

    @AfterViews
    void afterViews() {
        if (!utils.loadString(BacktoryConfig.ACCESS_TOKEN).equals(""))
            afterLogin();
        else
            buttonViewLoginAuth();
    }

    @Click
    public void buttonViewLoginAuth() {
        loginView.setVisibility(View.VISIBLE);
        registerView.setVisibility(View.GONE);
        bViewLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        bViewRegister.setBackgroundColor(ContextCompat.getColor(this, R.color.lightGray));
        bViewLogin.setTextColor(ContextCompat.getColor(this, R.color.white));
        bViewRegister.setTextColor(ContextCompat.getColor(this, R.color.titleGray));
    }

    @Click
    public void buttonViewRegisterAuth() {
        loginView.setVisibility(View.GONE);
        registerView.setVisibility(View.VISIBLE);
        bViewRegister.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        bViewLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.lightGray));
        bViewRegister.setTextColor(ContextCompat.getColor(this, R.color.white));
        bViewLogin.setTextColor(ContextCompat.getColor(this, R.color.titleGray));
    }

    @UiThread
    public void afterRegister(String phoneNumber, String password) {
        buttonViewLoginAuth();
        loginView.setAfterRegisterView(phoneNumber, password);
    }

    @UiThread
    public void afterLogin() {
        Intent intent = new Intent(this, GameActivity_.class);
        startActivity(intent);
        finish();
    }

    @UiThread
    public void afterChangePassword(String password) {
        loginView.afterChangePassword(password);
    }
}
