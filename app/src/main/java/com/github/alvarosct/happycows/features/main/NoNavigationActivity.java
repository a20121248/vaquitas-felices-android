package com.github.alvarosct.happycows.features.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.alvarosct.ascthelper.ui.activities.BaseActivity;
import com.github.alvarosct.happycows.R;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public abstract class NoNavigationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());

        tbPrimary = (Toolbar) findViewById(R.id.tb_primary);
        tbSecondary = (Toolbar) findViewById(R.id.tb_secondary);
        setSupportActionBar(getToolbar(true));

        getToolbar(true).setNavigationIcon(R.drawable.ic_back);
        getToolbar(true).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        setupContent();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public abstract int getLayout();

    public abstract void setupContent();

}
