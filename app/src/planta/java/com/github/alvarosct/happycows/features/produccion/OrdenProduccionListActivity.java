package com.github.alvarosct.happycows.features.produccion;

import android.support.v4.app.Fragment;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.main.NavigationActivity;

public class OrdenProduccionListActivity extends NavigationActivity {


    @Override
    public int getLayout() {
        return R.layout.base_activity_one_panel;
    }

    @Override
    public void setupContent() {
        Fragment fragment = new OrdenProduccionListFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);
    }

}
