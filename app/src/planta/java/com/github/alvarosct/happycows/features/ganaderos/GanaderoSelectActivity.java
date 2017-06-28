package com.github.alvarosct.happycows.features.ganaderos;

import android.support.v4.app.Fragment;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.main.NavigationActivity;
import com.github.alvarosct.happycows.features.porongos.PorongoListFragment;

public class GanaderoSelectActivity extends NavigationActivity {


    @Override
    public int getLayout() {
        return R.layout.base_activity_one_panel;
    }

    @Override
    public void setupContent() {
        Fragment fragment = new GanaderoSelectFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);
    }

}
