package ir.pegahtech.backtorysample.views.styledViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import ir.pegahtech.backtorysample.R;

public class StyledTextView extends TextView {

    public StyledTextView(Context context) {
        super(context);
        init();
    }

    public StyledTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StyledTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
//        setTextColor(getContext().getColor(R.color.titleGray));
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), getContext().getString(R.string.font_address)));
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    public void setPlainText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }
}
