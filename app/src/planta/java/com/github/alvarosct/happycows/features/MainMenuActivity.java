package com.github.alvarosct.happycows.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.insumos.InsumoListActivity;
import com.github.alvarosct.happycows.features.porongos.PorongoListActivity;
import com.github.alvarosct.happycows.features.sic.GanaderosSicActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_sic, R.id.bt_porongos, R.id.bt_qa})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.bt_sic:
                intent = new Intent(MainMenuActivity.this, GanaderosSicActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_porongos:
                intent = new Intent(MainMenuActivity.this, PorongoListActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_qa:
                intent = new Intent(MainMenuActivity.this, InsumoListActivity.class);
                startActivity(intent);
                break;
        }

        finish();
    }
}
