package com.github.alvarosct.ascthelper.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.alvarosct.ascthelper.R;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public abstract class BaseOnePanelChildActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_one_panel);

        tbPrimary = (Toolbar) findViewById(R.id.tb_primary);

        setSupportActionBar(getToolbar(true));
    }
}
