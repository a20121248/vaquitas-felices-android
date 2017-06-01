package com.github.alvarosct.happycows.features.porongos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.db.AppDatabase;
import com.github.alvarosct.happycows.db.models.Calidad;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalidadActivity extends AppCompatActivity {


    @BindView(R.id.et_color)
    EditText etColor;
    @BindView(R.id.et_olor)
    EditText etOlor;
    @BindView(R.id.et_alcohol)
    EditText etAlcohol;
    @BindView(R.id.et_brix)
    EditText etBrix;

    private AppDatabase db;
    private int porongoId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality);
        ButterKnife.bind(this);

        porongoId = getIntent().getExtras().getInt("ID", -1);

        db = AppDatabase.getInstance(CalidadActivity.this);
    }

    @OnClick(R.id.bt_send)
    public void onViewClicked() {
        Calidad c = new Calidad();
        c.setId(db.calidadModel().getAll().size() + 1);
        c.setPorongoId(porongoId);

        c.setColor(etColor.getText().toString());
        c.setOlor(etOlor.getText().toString());
        c.setAlcohol(etAlcohol.getText().toString());
        c.setBrix(etBrix.getText().toString());

        db.calidadModel().insert(c);
        finish();
    }
}
