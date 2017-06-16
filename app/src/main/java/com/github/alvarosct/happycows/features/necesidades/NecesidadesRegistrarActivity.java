package com.github.alvarosct.happycows.features.necesidades;

import android.support.v4.app.Fragment;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.degustaciones.DegustacionesRegistrarFragment;
import com.github.alvarosct.happycows.features.main.NavigationActivity;

public class NecesidadesRegistrarActivity extends NavigationActivity {

    @Override
    public int getLayout() {
        return R.layout.base_activity_one_panel;
    }

    @Override
    public void setupContent() {

        setTitle("Registrar Necesidades");

        Fragment fragment = new NecesidadesRegistrarFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);

    }
}
