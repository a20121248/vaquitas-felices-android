package com.github.alvarosct.ascthelper.utils.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.R;

/**
 * Created by Android-Dev on 12/04/2017.
 */


public class DialogLoading extends Dialog {

    private String message;

    public DialogLoading(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loading);

        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                TextView tv_message = (TextView) findViewById(R.id.tv_message);
                tv_message.setText(message);
            }
        });
    }

    public void setMessage(String msg) {
        message = msg;
    }

}