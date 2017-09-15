package com.github.alvarosct.happycows.features.produccion.detail;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.main.NoNavigationActivity;

public class OrdenProduccionViewActivity extends NoNavigationActivity {


    @Override
    public int getLayout() {
        return R.layout.base_activity_one_panel;
    }

    OrdenProduccionViewFragment fragment;

    @Override
    public void setupContent() {

        fragment = new OrdenProduccionViewFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;

        if (requestCode == Constants.INTENT_INGREDIENTE){
            fragment.listInsumos();

        } else if (requestCode == Constants.INTENT_PROCESO){
            fragment.listProcesos();
        }
    }


}
