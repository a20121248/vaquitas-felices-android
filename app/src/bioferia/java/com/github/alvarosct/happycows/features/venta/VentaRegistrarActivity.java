package com.github.alvarosct.happycows.features.venta;

import android.support.v4.app.Fragment;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.main.NavigationActivity;

public class VentaRegistrarActivity extends NavigationActivity {

    @Override
    public int getLayout() {
        return R.layout.base_activity_one_panel;
    }

    @Override
    public void setupContent() {

        setTitle("Registrar Venta");

        Fragment fragment = new VentaRegistrarFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);

    }
}
