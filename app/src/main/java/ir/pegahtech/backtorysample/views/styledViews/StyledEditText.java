package ir.pegahtech.backtorysample.views.styledViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import ir.pegahtech.backtorysample.R;

public class StyledEditText extends EditText {

    public StyledEditText(Context context) {
        super(context);
        init();
    }

    public StyledEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StyledEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), getContext().getString(R.string.font_address)));
    }
}
