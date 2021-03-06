package com.github.alvarosct.happycows.features.syncDatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.alvarosct.ascthelper.ui.activities.BaseOnePanelChildActivity;
import com.github.alvarosct.happycows.R;

import butterknife.ButterKnife;


public class SyncDatabaseActivity extends BaseOnePanelChildActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = new SyncDatabaseFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);
    }

}
