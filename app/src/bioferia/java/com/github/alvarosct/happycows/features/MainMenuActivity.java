package com.github.alvarosct.happycows.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.degustaciones.DegustacionesRegistrarActivity;
import com.github.alvarosct.happycows.features.materiales.MaterialesRegistrarActivity;
import com.github.alvarosct.happycows.features.necesidades.NecesidadesRegistrarActivity;
import com.github.alvarosct.happycows.features.usuario.UsuarioRegistrarActivity;
import com.github.alvarosct.happycows.features.venta.list.VentasListActivity;
import com.github.alvarosct.happycows.features.venta.register.VentaRegistrarActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_venta, R.id.bt_materiales, R.id.bt_list_venta, R.id.bt_new_user, R.id.bt_necesidad, R.id.bt_degustaciones})
    public void onViewClicked(View view) {
        Intent intent;
//        MenuHandler.getInstance().pickIntent(MainMenuActivity.this, 1, )
        switch (view.getId()) {
            case R.id.bt_venta:
                intent = new Intent(MainMenuActivity.this, VentaRegistrarActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_list_venta:
                intent = new Intent(MainMenuActivity.this, VentasListActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_materiales:
                intent = new Intent(MainMenuActivity.this, MaterialesRegistrarActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_new_user:
                intent = new Intent(MainMenuActivity.this, UsuarioRegistrarActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_necesidad:
                intent = new Intent(MainMenuActivity.this, NecesidadesRegistrarActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_degustaciones:
                intent = new Intent(MainMenuActivity.this, DegustacionesRegistrarActivity.class);
                startActivity(intent);
                break;
        }

        finish();
    }
}
