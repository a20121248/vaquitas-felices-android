package com.github.alvarosct.happycows.features.porongos;

import android.support.v4.app.Fragment;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.main.NoNavigationActivity;

public class PorongoViewActivity extends NoNavigationActivity {

    @Override
    public int getLayout() {
        return R.layout.base_activity_one_panel;
    }

    @Override
    public void setupContent() {
        Fragment fragment = new PorongoViewFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);
    }
}