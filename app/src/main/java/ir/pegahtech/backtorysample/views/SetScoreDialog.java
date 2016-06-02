package ir.pegahtech.backtorysample.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import ir.pegahtech.backtorysample.R;

/**
 * Created by SirGozal on 2/10/2016.
 */

@EBean
public class SetScoreDialog {
    Dialog dialog;

    @RootContext
    Context context;

    @RootContext
    Activity activity;

    @UiThread
    public void build() {
        try {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    dismiss();
                }
            });
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(R.layout.view_set_score_dialog);
            Button bSearchAgain = (Button) dialog.findViewById(R.id.buttonOkSetScore);
            bSearchAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @UiThread
    void dismiss() {
        dialog.dismiss();
    }
}
