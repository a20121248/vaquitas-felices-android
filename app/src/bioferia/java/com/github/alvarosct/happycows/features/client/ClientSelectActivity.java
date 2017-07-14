package com.github.alvarosct.happycows.features.client;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.alvarosct.ascthelper.ui.activities.BaseActivity;
import com.github.alvarosct.happycows.R;

public class ClientSelectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_one_panel);

        setupContent();
    }

    private void setupContent() {
        Fragment fragment = new ClientSelectFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
