package com.github.alvarosct.happycows.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.insumos.InsumoListActivity;
import com.github.alvarosct.happycows.features.porongos.GanaderosPorongosActivity;
import com.github.alvarosct.happycows.features.sic.GanaderosSicActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_sic, R.id.bt_qa, R.id.bt_insumos})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.bt_sic:
                intent = new Intent(MainActivity.this, GanaderosSicActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_insumos:
                intent = new Intent(MainActivity.this, InsumoListActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_qa:
                intent = new Intent(MainActivity.this, GanaderosPorongosActivity.class);
                startActivity(intent);
                break;
        }
    }
}
