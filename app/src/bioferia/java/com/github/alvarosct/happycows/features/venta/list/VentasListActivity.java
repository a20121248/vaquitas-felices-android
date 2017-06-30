package com.github.alvarosct.happycows.features.venta.list;

import android.support.v4.app.Fragment;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.main.NavigationActivity;

public class VentasListActivity extends NavigationActivity {

    @Override
    public int getLayout() {
        return R.layout.base_activity_one_panel;
    }

    @Override
    public void setupContent() {

        setTitle("Listar Venta");

        Fragment fragment = new VentasListFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);

    }
}