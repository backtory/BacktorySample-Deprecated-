package ir.pegahtech.backtorysample.views;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import ir.pegahtech.backtorysample.R;


/**
 * Created by SirGozal on 2/20/2016.
 */

@EBean
public class LoadingDialog {
    Dialog dialog;

    @RootContext
    Context context;

    @AfterInject
    void afterInject() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.view_loading);
    }

    @UiThread
    public void show() {
        dialog.show();
    }

    @UiThread
    public void hide() {
        dialog.hide();
    }
}
