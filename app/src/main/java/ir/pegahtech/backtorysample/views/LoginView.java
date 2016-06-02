package ir.pegahtech.backtorysample.views;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import ir.pegahtech.backtorysample.R;
import ir.pegahtech.backtorysample.activities.AuthActivity;
import ir.pegahtech.backtorysample.managers.BacktoryConfig;
import ir.pegahtech.backtorysample.models.LoginResponse;
import ir.pegahtech.backtorysample.network.AuthenticationServiceInterface;
import ir.pegahtech.backtorysample.network.BacktoryErrorHandler;
import ir.pegahtech.backtorysample.utils.Utils;

/**
 * Created by SirGozal on 2/9/2016.
 */

@EBean
public class LoginView {
    @RootContext
    Context context;

    @RootContext
    AuthActivity authActivity;

    @RestService
    AuthenticationServiceInterface authenticationServiceInterface;

    @ViewById(R.id.buttonLogin)
    Button bLogin;

    @ViewById(R.id.editUsernameLogin)
    EditText etUsername;

    @ViewById(R.id.editPasswordLogin)
    EditText etPassword;

    @ViewById(R.id.viewLoginAuth)
    View vLogin;

    @Bean
    BacktoryErrorHandler backtoryErrorHandler;

    @Bean
    Utils utils;

    @Bean
    LoadingDialog loadingDialog;

    @AfterViews
    void afterView() {
        setVisibility(View.VISIBLE);
        authenticationServiceInterface.setRestErrorHandler(backtoryErrorHandler);
    }

    public void setVisibility(int visibility) {
        if (vLogin != null)
            vLogin.setVisibility(visibility);
    }

    @Click
    void buttonLogin() {
        login();
    }

    @Background
    void login() {
        loadingDialog.show();
        try {
            ResponseEntity<LoginResponse> authenticationResponseEntity = authenticationServiceInterface.
                    login(etUsername.getText().toString(), etPassword.getText().toString());
            utils.saveString(BacktoryConfig.ACCESS_TOKEN,
                    authenticationResponseEntity.getBody().getAccess_token());
            successfulLogin();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                utils.toast(context.getString(R.string.login_failed));
            }
        }
        loadingDialog.hide();
    }

    @UiThread
    void successfulLogin() {
        utils.toast(context.getString(R.string.auth_successful_login));
        authActivity.afterLogin();
    }

    public void setAfterRegisterView(String phoneNumber, String password) {
        etUsername.setText(phoneNumber);
        etPassword.setText(password);
    }

    public void afterChangePassword(String password) {
        etPassword.setText(password);
    }
}
