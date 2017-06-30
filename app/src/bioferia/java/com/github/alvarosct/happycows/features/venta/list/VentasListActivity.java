package com.github.alvarosct.happycows.features.venta.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.alvarosct.ascthelper.ui.activities.BaseActivity;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.productos.ProductoSelectFragment;

public class VentasListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_one_panel);

        setupContent();
    }

    private void setupContent() {
        Fragment fragment = new VentasListFragment();
        fragment.setArguments(getIntent().getExtras());
        showFragment(fragment, true);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
