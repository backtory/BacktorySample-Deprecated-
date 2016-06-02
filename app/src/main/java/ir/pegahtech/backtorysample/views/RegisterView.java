package ir.pegahtech.backtorysample.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

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
import ir.pegahtech.backtorysample.models.RegisterRequest;
import ir.pegahtech.backtorysample.models.RegisterResponse;
import ir.pegahtech.backtorysample.network.AuthenticationServiceInterface;
import ir.pegahtech.backtorysample.network.BacktoryErrorHandler;
import ir.pegahtech.backtorysample.utils.Utils;

/**
 * Created by SirGozal on 2/9/2016.
 */

@EBean
public class RegisterView {
    @RootContext
    Context context;

    @RootContext
    AuthActivity authActivity;

    @ViewById(R.id.buttonRegister)
    Button bRegister;

    @ViewById(R.id.editFirstNameRegister)
    EditText etFirstName;

    @ViewById(R.id.editLastNameRegister)
    EditText etLastName;

    @ViewById(R.id.editUsernameRegister)
    EditText etUsername;

    @ViewById(R.id.editPasswordRegister)
    EditText etPassword;

    @ViewById(R.id.editRepeatPasswordRegister)
    EditText etRepeatPassword;

    @ViewById(R.id.viewRegisterAuth)
    View vRegister;

    @Bean
    Utils utils;

    @Bean
    BacktoryErrorHandler backtoryErrorHandler;

    @Bean
    LoadingDialog loadingDialog;

    @RestService
    AuthenticationServiceInterface authenticationServiceInterface;

    @AfterViews
    void afterView() {
        authenticationServiceInterface.setRestErrorHandler(backtoryErrorHandler);
    }

    public void setVisibility(int visibility) {
        if (vRegister != null)
            vRegister.setVisibility(visibility);
    }

    @Click
    void buttonRegister() {
        checkRegisterInfo();
    }

    private void checkRegisterInfo() {
        RegisterRequest registerRequest = new RegisterRequest();
        String firstName = etFirstName.getText().toString(),
                lastName = etLastName.getText().toString(),
                username = etUsername.getText().toString(),
                password = etPassword.getText().toString(),
                repeatPassword = etRepeatPassword.getText().toString();
        if (!password.equals(repeatPassword)) {
            utils.toast(context.getString(R.string.auth_password_not_match));
            return;
        }
        if (username.equals("")) {
            utils.toast(context.getString(R.string.auth_enter_username));
            return;
        }
        registerRequest.setFirstName(firstName);
        registerRequest.setLastName(lastName);
        registerRequest.setUsername(username);
        registerRequest.setPassword(password);
        register(registerRequest);
    }

    @Background
    void register(RegisterRequest registerRequest) {
        loadingDialog.show();
        try {
            ResponseEntity<RegisterResponse> responseEntity = authenticationServiceInterface.register(registerRequest);
            successfulRegister(registerRequest);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                utils.toast(context.getString(R.string.server_error));
            } else if (e.getStatusCode() == HttpStatus.CONFLICT) {
                utils.toast(context.getString(R.string.auth_prev_sign_up));
            }
        }
        loadingDialog.hide();
    }

    @UiThread
    void successfulRegister(RegisterRequest registerRequest) {
        utils.toast(context.getString(R.string.auth_successful_sign_up));
        authActivity.afterRegister(registerRequest.getUsername(), registerRequest.getPassword());
    }
}
