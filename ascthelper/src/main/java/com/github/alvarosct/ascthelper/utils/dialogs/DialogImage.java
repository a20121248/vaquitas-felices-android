package com.github.alvarosct.ascthelper.utils.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.ImageView;

import com.github.alvarosct.ascthelper.R;

/**
 * Created by Android-Dev on 12/04/2017.
 */

public class DialogImage extends Dialog {

    private IDisplay iDisplay;
    private ImageView ivDisplay;

    public DialogImage(@NonNull Context context, IDisplay iDisplay) {
        super(context);
        this.iDisplay = iDisplay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_image);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setupViews();
        setCancelable(true);
        iDisplay.displayImage(ivDisplay);
    }

    private void setupViews() {
        ivDisplay = (ImageView) findViewById(R.id.iv_display);
    }

    public interface IDisplay {
        void displayImage(ImageView iv);
    }
}