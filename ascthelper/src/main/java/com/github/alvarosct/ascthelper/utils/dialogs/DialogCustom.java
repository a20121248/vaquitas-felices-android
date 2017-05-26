package com.github.alvarosct.ascthelper.utils.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.R;

/**
 * Created by Android-Dev on 12/04/2017.
 */

public class DialogCustom extends Dialog {

    private TextView tvTitle;
    private TextView tvMessage;
    private TextView btPositive;
    private TextView btNegative;

    private String title;
    private String message;
    private ButtonBehaviour negativeBehaviour;
    private ButtonBehaviour positiveBehaviour;

    public DialogCustom(Context context, String title, String message, ButtonBehaviour positiveBehaviour) {
        super(context);
        init(title, message, positiveBehaviour, null);
    }

    public DialogCustom(Context context, String title, String message, ButtonBehaviour positiveBehaviour, String negativeMessage) {
        super(context);
        init(title, message, positiveBehaviour, new ButtonBehaviour(negativeMessage));
    }

    public void init(String title, String message, ButtonBehaviour positiveBehaviour, ButtonBehaviour negativeBehaviour) {
        this.title = title;
        this.message = message;
        this.negativeBehaviour = negativeBehaviour;
        this.positiveBehaviour = positiveBehaviour;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_default);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        setupViews();

        setCancelable(false);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(message)) {
            tvMessage.setText(message);
        }
        setButton(btNegative, negativeBehaviour);
        setButton(btPositive, positiveBehaviour);
    }

    private void setupViews() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        btPositive = (TextView) findViewById(R.id.bt_positive);
        btNegative = (TextView) findViewById(R.id.bt_negative);
    }

    public void setButton(TextView button, final ButtonBehaviour buttonBehaviour) {
        if (buttonBehaviour != null) {
            button.setVisibility(View.VISIBLE);
            button.setText(buttonBehaviour.label);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogCustom.this.dismiss();
                    DialogCustom.this.cancel();
                    buttonBehaviour.iButton.onButtonClick();
                }
            });
        } else {
            button.setVisibility(View.GONE);
        }
    }

    public static class ButtonBehaviour {
        private String label;
        private IButton iButton;

        public ButtonBehaviour(String buttonLabel, @NonNull IButton iButton) {
            this.label = buttonLabel;
            this.iButton = iButton;
        }

        public ButtonBehaviour(String buttonLabel) {
            this.label = buttonLabel;
            this.iButton = new IButton() {
                @Override
                public void onButtonClick() {
//                        Do nothing
                }
            };
        }
    }

    public interface IButton {
        void onButtonClick();
    }
}