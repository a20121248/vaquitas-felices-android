package com.github.alvarosct.happycows.features.produccion.detail.insumo;

import android.support.v4.app.Fragment;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.main.NoNavigationActivity;

public class InsumoFormActivity extends NoNavigationActivity {


    @Override
    public int getLayout() {
        return R.layout.base_activity_one_panel;
    }

    @Override
    public void setupContent() {
        Fragment fragment = new InsumoFormFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);
    }

}
