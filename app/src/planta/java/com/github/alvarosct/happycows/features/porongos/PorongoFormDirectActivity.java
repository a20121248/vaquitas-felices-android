package com.github.alvarosct.happycows.features.porongos;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.MainMenuActivity;
import com.github.alvarosct.happycows.features.main.NavigationActivity;
import com.github.alvarosct.happycows.features.main.NoNavigationActivity;

public class PorongoFormDirectActivity extends NavigationActivity {

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
    public void onBack() {
        new DialogCustom(getContext(),
                "¡Atención!", "¿Deseas descartar los cambios?",
                new DialogCustom.ButtonBehaviour("Si", new DialogCustom.IButton() {
                    @Override
                    public void onButtonClick() {
                        closeActivity();
                    }
                }), "No").show();
    }

    @Override
    public void closeActivity() {
        startActivity(new Intent(getContext(), MainMenuActivity.class));
        finish();
    }
}