package com.github.alvarosct.happycows.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.R;

/**
 * Created by Android-Dev on 28/06/2017.
 */

public class CustomTextView extends LinearLayout {

    private TextView tvHint;
    private TextView tvText;

    private String hint = "";
    private String text = "";
    private float hintSizePx = 0f;
    private float textSizePx = 0f;

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        handleAttributes(context, attrs);
        init();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleAttributes(context, attrs);
        init();
    }

//    Only for API21 or Above
//    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void handleAttributes(Context context, AttributeSet attrs) {
        try {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
            {
                hint = styledAttrs.getString(R.styleable.CustomTextView_hint);
                text = styledAttrs.getString(R.styleable.CustomTextView_text);
                hintSizePx = styledAttrs.getDimension(R.styleable.CustomTextView_hint_size, UtilMethods.pxFromSp(context,12));
                textSizePx = styledAttrs.getDimension(R.styleable.CustomTextView_text_size, UtilMethods.pxFromSp(context,14));
            }
            styledAttrs.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        inflate(getContext(), R.layout.layout_custom_text_view, this);

        setOrientation(LinearLayout.VERTICAL);

        this.tvHint = (TextView) findViewById(R.id.tv_hint);
        this.tvText = (TextView) findViewById(R.id.tv_text);
//
        tvText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePx);
        tvHint.setTextSize(TypedValue.COMPLEX_UNIT_PX, hintSizePx);
//        tvHint.setTextSize(hintSizePx);

        setHint(hint);
        setText(text);
    }

    public void setText(String string) {
        tvText.setText(string);
    }

    public void setHint(String string) {
        tvHint.setText(string);
    }

}
