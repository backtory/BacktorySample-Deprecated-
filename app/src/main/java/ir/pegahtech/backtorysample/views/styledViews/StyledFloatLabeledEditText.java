package ir.pegahtech.backtorysample.views.styledViews;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.wrapp.floatlabelededittext.FloatLabeledEditText;

import ir.pegahtech.backtorysample.R;

/**
 * Created by Mohammad on 9/24/2015.
 */
public class StyledFloatLabeledEditText extends FloatLabeledEditText {

    public StyledFloatLabeledEditText(Context context) {
        super(context);
        init();
    }

    public StyledFloatLabeledEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StyledFloatLabeledEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init() {
        TextView tvHint = (TextView) getChildAt(0);
        tvHint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), getContext().getString(R.string.font_address)));
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 20, 0);
        tvHint.setLayoutParams(layoutParams);
        tvHint.setGravity(Gravity.RIGHT);
    }
}
