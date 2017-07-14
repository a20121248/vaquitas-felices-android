package com.github.alvarosct.happycows.utils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.R;


/**
 * Created by Android-Dev on 28/06/2017.
 */

public class CustomSpinner extends LinearLayout {

    private TextView tvLabel;
    private TextView tvError;
    private MySpinner spinner;

    private String label = "";
    private String error = "";

    public CustomSpinner(Context context) {
        super(context);
        init();
    }

    public CustomSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        handleAttributes(context, attrs);
        init();
    }

    public CustomSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleAttributes(context, attrs);
        init();
    }

//    Only for API21 or Above
//    public CustomSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void handleAttributes(Context context, AttributeSet attrs) {
        try {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.Custom);
            {
                error = styledAttrs.getString(R.styleable.Custom_error);
                label = styledAttrs.getString(R.styleable.Custom_label);
            }
            styledAttrs.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
//        Container
        setOrientation(LinearLayout.VERTICAL);
        setPadding(0, 0, 0, UtilMethods.pxFromDp(getContext(), 16));

//        Label
        tvLabel = new TextView(getContext());
        tvLabel.setText(label);
        tvLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
//        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/ubuntu_light.ttf");
//        tvLabel.setTypeface(myTypeface);
        addView(tvLabel);

//        Spinner
        spinner = new MySpinner(getContext(), Spinner.MODE_DROPDOWN);
        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        params.setMargins(UtilMethods.pxFromDp(getContext(), 4), 0,
                UtilMethods.pxFromDp(getContext(), 4), 0);
        spinner.setLayoutParams(params);
        addView(spinner);

//        Error Label
        tvError = new TextView(getContext());
        tvError.setPadding(UtilMethods.pxFromDp(getContext(), 4),
                UtilMethods.pxFromDp(getContext(), 4), 0, 0);
        tvError.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        tvError.setText(error);
        tvError.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        addView(tvError);

        setErrorEnable(false);
    }


    public boolean isValid(){
        return !getSpinner().errorEnable;
    }

    public MySpinner getSpinner() {
        return spinner;
    }

    public void setError(String string) {
        tvError.setText(string);
    }

    public void setErrorEnable(boolean enable) {
        tvError.setVisibility(enable ? VISIBLE : GONE);
        spinner.setErrorEnable(enable);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        getSpinner().setEnabled(enabled);
    }

    public class MySpinner extends android.support.v7.widget.AppCompatSpinner {

        public MySpinner(Context context, int mode) {
            super(context, mode);
            init();
        }

        public MySpinner(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private Paint paintGreen70;
        private Paint paintGreen62;
        private Paint paintGreen16;
        private Paint paintRed70;
        private Paint paintRed62;
        private int lineHeight;

        private void init() {
            paintGreen70 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintGreen70.setColor(ContextCompat.getColor(CustomSpinner.this.getContext(), R.color.green_70));
            paintGreen62 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintGreen62.setColor(ContextCompat.getColor(CustomSpinner.this.getContext(), R.color.green_62));
            paintGreen16 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintGreen16.setColor(ContextCompat.getColor(CustomSpinner.this.getContext(), R.color.green_16));
            paintRed70 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintRed70.setColor(ContextCompat.getColor(CustomSpinner.this.getContext(), R.color.red_70));
            paintRed62 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintRed62.setColor(ContextCompat.getColor(CustomSpinner.this.getContext(), R.color.red_62));
            lineHeight = 2;

            this.setPadding(0, 4, 0, 4);
            setBackgroundResource(R.color.zero);
        }

        private boolean errorEnable = false;

        public void setErrorEnable(boolean flag) {
            errorEnable = flag;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if (isEnabled()) {
                if (!errorEnable) {

                    drawTriangle(canvas, paintGreen70,
                            getWidth() - 20, getHeight() / 2, 8, 6);

//                    Top Color
                    canvas.drawRect(0, getHeight() - lineHeight, getWidth(),
                            getHeight() - lineHeight / 2, paintGreen62);
//                    Bottom Color
                    canvas.drawRect(0, getHeight() - lineHeight / 2, getWidth(),
                            getHeight(), paintGreen70);
                } else {

                    drawTriangle(canvas, paintRed70,
                            getWidth() - 20, getHeight() / 2, 8, 8);

//                    Top Color
                    canvas.drawRect(0, getHeight() - lineHeight, getWidth(),
                            getHeight() - lineHeight / 2, paintRed62);
//                    Bottom Color
                    canvas.drawRect(0, getHeight() - lineHeight / 2, getWidth(),
                            getHeight(), paintRed70);

                }
            } else {
//                No line
//                canvas.drawRect(0, getHeight() - lineHeight, getWidth(), getHeight(), paintGreen16);
            }
        }

        private void drawTriangle(Canvas canvas, Paint paint, float center_x, float center_y, int width, int height) {
            Path path = new Path();
            path.moveTo(center_x, center_y + height / 2);
            path.lineTo(center_x + width / 2, center_y - height / 2);
            path.lineTo(center_x - width / 2, center_y - height / 2);
            path.close();

            canvas.drawPath(path, paint);
        }
    }

}