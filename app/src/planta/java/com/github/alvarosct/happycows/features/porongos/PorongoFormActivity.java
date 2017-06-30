package com.github.alvarosct.happycows.features.porongos;

import android.support.v4.app.Fragment;

import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.main.NoNavigationActivity;

public class PorongoFormActivity extends NoNavigationActivity {

    @Override
    public int getLayout() {
        return R.layout.base_activity_one_panel;
    }

    @Override
    public void setupContent() {
        Fragment fragment = new PorongoFormFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);
    }

    @Override
    public void onBackPressed() {
        new DialogCustom(getContext(),
                "¡Atención!", "¿Deseas descartar los cambios?",
                new DialogCustom.ButtonBehaviour("Si", new DialogCustom.IButton() {
                    @Override
                    public void onButtonClick() {
                        finish();
                    }
                }), "No").show();
    }
}