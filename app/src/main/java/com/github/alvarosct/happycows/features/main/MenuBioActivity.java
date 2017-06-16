package com.github.alvarosct.happycows.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.materiales.MaterialesRegistrarActivity;
import com.github.alvarosct.happycows.features.usuarios.registrar.UsuarioRegistrarActivity;
import com.github.alvarosct.happycows.features.venta.registrar.VentaRegistrarActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuBioActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bio);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_venta, R.id.bt_materiales, R.id.bt_new_user, R.id.bt_necesidad, R.id.bt_degustaciones})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.bt_venta:
                intent = new Intent(MenuBioActivity.this, VentaRegistrarActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_materiales:
                intent = new Intent(MenuBioActivity.this, MaterialesRegistrarActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_new_user:
                intent = new Intent(MenuBioActivity.this, UsuarioRegistrarActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_necesidad:
                intent = new Intent(MenuBioActivity.this, MaterialesRegistrarActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_degustaciones:
                intent = new Intent(MenuBioActivity.this, MaterialesRegistrarActivity.class);
                startActivity(intent);
                break;
        }

        finish();
    }
}
