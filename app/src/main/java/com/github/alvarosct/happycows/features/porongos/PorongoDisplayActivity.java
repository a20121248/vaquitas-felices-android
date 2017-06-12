package com.github.alvarosct.happycows.features.porongos;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PorongoDisplayActivity extends AppCompatActivity {

    @BindView(R.id.et_ganadero)
    TextInputEditText etGanadero;
    @BindView(R.id.et_peso)
    TextInputEditText etPeso;
    @BindView(R.id.et_color)
    TextInputEditText etColor;
    @BindView(R.id.et_olor)
    TextInputEditText etOlor;
    @BindView(R.id.ly_quality_1)
    LinearLayout lyQuality1;
    @BindView(R.id.et_alcohol)
    TextInputEditText etAlcohol;
    @BindView(R.id.ly_quality_2)
    LinearLayout lyQuality2;
    @BindView(R.id.et_temperatura)
    TextInputEditText etTemperatura;
    @BindView(R.id.et_ph)
    TextInputEditText etPh;
    @BindView(R.id.et_acidez)
    TextInputEditText etAcidez;
    @BindView(R.id.et_limpieza)
    TextInputEditText etLimpieza;
    @BindView(R.id.et_brix)
    TextInputEditText etBrix;
    @BindView(R.id.et_densidad)
    TextInputEditText etDensidad;
    @BindView(R.id.ly_quality_3)
    LinearLayout lyQuality3;
    @BindView(R.id.bt_quality)
    Button btQuality;
    @BindView(R.id.bt_done)
    Button btDone;

    private Porongo porongo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.porongo_display);
        String porongoString = getIntent().getStringExtra("ENTITY");
        porongo = new Gson().fromJson(porongoString, Porongo.class);

        configButton();

        ButterKnife.bind(this);
    }

    private void configButton() {
        if (porongo.getStep() == 0) {
            if (porongo.getColor().equals(Porongo.WHITE) && porongo.getOlor().equals(Porongo.GOOD)) {
                btQuality.setVisibility(View.GONE);
                btDone.setVisibility(View.VISIBLE);
            } else {
                btQuality.setVisibility(View.VISIBLE);
                btDone.setVisibility(View.GONE);
            }
        }
        if (porongo.getStep() == 0) {
            if (porongo.getColor().equals(Porongo.WHITE) && porongo.getOlor().equals(Porongo.GOOD)) {
                btQuality.setVisibility(View.GONE);
                btDone.setVisibility(View.VISIBLE);
            } else {
                btQuality.setVisibility(View.VISIBLE);
                btDone.setVisibility(View.GONE);
            }
        }
    }

    @OnClick(R.id.bt_quality)
    public void onBtQualityClicked() {
    }

    @OnClick(R.id.bt_done)
    public void onBtDoneClicked() {

    }
}
