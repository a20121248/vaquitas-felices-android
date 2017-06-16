package com.github.alvarosct.happycows.features.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.alvarosct.ascthelper.ui.activities.BaseActivity;
import com.github.alvarosct.happycows.R;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public abstract class NavigationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MenuHandler menuHandler = MenuHandler.getInstance(this);
        setContentView(menuHandler.getView(this, getLayout()));

        tbPrimary = (Toolbar) findViewById(R.id.tb_primary);
        tbSecondary = (Toolbar) findViewById(R.id.tb_secondary);
        setSupportActionBar(getToolbar(true));

//        TODO: Improve this call
        menuHandler.initialize(this, getToolbar(true));

        setupContent();
    }

    public abstract int getLayout();

    public abstract void setupContent();

    @Override
    public final void onBackPressed() {
        if (!MenuHandler.getInstance(this).onBackPressed()) {
            onBack();
        }
    }

    protected void onBack() {
        super.onBackPressed();
    }
}
