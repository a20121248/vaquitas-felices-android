package com.github.alvarosct.happycows.features.porongos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.BaseFormManager;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.features.ganaderos.GanaderoSelectActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PorongoFormFragment extends BaseFragment {

    Unbinder unbinder;

    @BindView(R.id.tv_ganadero)
    TextView tvGanadero;
    @BindView(R.id.et_peso)
    EditText etPeso;

    @BindView(R.id.bt_white)
    Button btWhite;
    @BindView(R.id.bt_good)
    Button btGood;


    @BindView(R.id.ly_calidad_2)
    LinearLayout lyCalidad2;
    @BindView(R.id.et_alcohol)
    EditText etAlcohol;


    @BindView(R.id.ly_calidad_3)
    LinearLayout lyCalidad3;
    @BindView(R.id.et_acidez)
    EditText etAcidez;
    @BindView(R.id.et_densidad)
    EditText etDensidad;
    @BindView(R.id.et_brix)
    EditText etBrix;
    @BindView(R.id.et_ph)
    EditText etPh;
    @BindView(R.id.et_limpieza)
    EditText etLimpieza;

    protected Porongo entity;
    private Button selectedColor, selectedOlor;

    public PorongoFormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registrar Ingreso");
        setHasOptionsMenu(true);

        onViewClickedGanadero();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.porongo_form, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupVariables() {
        super.setupVariables();
        int id = getArguments().getInt(Constants.BUNDLE_ENTITY_ID);
        entity = (id == -1) ? new Porongo() :
                AppDatabase.getInstance().porongoModel().getById(id);
    }

    private void setGanaderoNombre() {

        String ganaderoString = "Seleccionar";
        if (entity.getGanaderoId() != 0) {
            ganaderoString = AppDatabase.getInstance().ganaderoModel().
                    getById(entity.getGanaderoId()).getNombres();
        }

        tvGanadero.setText(ganaderoString);

    }

    private void enableCalidad2() {
        if (selectedColor == null || selectedOlor == null) return;

        if (selectedColor.getId() == R.id.bt_white &&
                selectedOlor.getId() == R.id.bt_good) {

            lyCalidad2.setVisibility(View.GONE);
            lyCalidad3.setVisibility(View.GONE);
        } else {
            lyCalidad2.setVisibility(View.VISIBLE);
            enableCalidad3();
        }

    }

    private void enableCalidad3() {

        if (entity.getAlcohol() > Porongo.MIN_ALCOHOL && entity.getAlcohol() < Porongo.MAX_ALCOHOL) {
            lyCalidad3.setVisibility(View.GONE);
        } else {
            lyCalidad3.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void setupView(View view) {

//        Defaults
        setGanaderoNombre();
        onViewClickedColor(btWhite);
        onViewClickedOlor(btGood);

        BaseFormManager baseFormManager = new BaseFormManager(getContext());

        baseFormManager.setupEditTextNumber(etPeso, String.valueOf(entity.getPeso()),
                new BaseFormManager.IEditText() {
                    @Override
                    public void setValue(String value) {
                        entity.setPeso(Double.parseDouble(value));
                    }
                });
        baseFormManager.setupEditTextNumber(etAlcohol, String.valueOf(entity.getAlcohol()),
                new BaseFormManager.IEditText() {
                    @Override
                    public void setValue(String value) {
                        entity.setAlcohol(Double.parseDouble(value));
                        enableCalidad3();
                    }
                });

        baseFormManager.setupEditTextNumber(etAcidez, String.valueOf(entity.getAcidez()),
                new BaseFormManager.IEditText() {
                    @Override
                    public void setValue(String value) {
                        entity.setAcidez(Integer.parseInt(value));
                    }
                });

        baseFormManager.setupEditTextNumber(etDensidad, String.valueOf(entity.getDensidad()),
                new BaseFormManager.IEditText() {
                    @Override
                    public void setValue(String value) {
                        entity.setDensidad(Integer.parseInt(value));
                    }
                });

        baseFormManager.setupEditTextNumber(etBrix, String.valueOf(entity.getBrix()),
                new BaseFormManager.IEditText() {
                    @Override
                    public void setValue(String value) {
                        entity.setBrix(Double.parseDouble(value));
                    }
                });

        baseFormManager.setupEditTextNumber(etPh, String.valueOf(entity.getPh()),
                new BaseFormManager.IEditText() {
                    @Override
                    public void setValue(String value) {
                        entity.setPh(Double.parseDouble(value));
                    }
                });

        baseFormManager.setupEditTextNumber(etLimpieza, String.valueOf(entity.getLimpieza()),
                new BaseFormManager.IEditText() {
                    @Override
                    public void setValue(String value) {
                        entity.setLimpieza(Integer.parseInt(value));
                    }
                });

//        tvAcidez.setText(String.valueOf(entity.getPorongo().getAcidez()));
//        tvAlcohol.setText(String.valueOf(entity.getPorongo().getAlcohol()));
//        tvDensidad.setText(String.valueOf(entity.getPorongo().getDensidad()));
//        tvBrix.setText(String.valueOf(entity.getPorongo().getBrix()));
//        tvPh.setText(String.valueOf(entity.getPorongo().getPh()));
//        tvLimpieza.setText(String.valueOf(entity.getPorongo().getLimpieza()));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.INTENT_SELECT_GANADERO) {
                int ganaderoId = data.getIntExtra(Constants.BUNDLE_ENTITY_ID, -1);
                if (ganaderoId != -1) entity.setGanaderoId(ganaderoId);
                setGanaderoNombre();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ly_ganadero)
    public void onViewClickedGanadero() {
        Intent intent = new Intent(getContext(), GanaderoSelectActivity.class);
        startActivityForResult(intent, Constants.INTENT_SELECT_GANADERO);
    }


    @OnClick({R.id.bt_white, R.id.bt_yellow, R.id.bt_red})
    public void onViewClickedColor(Button button) {
        if (selectedColor != null) {
            selectedColor.setSelected(false);
        }
        selectedColor = button;
        button.setSelected(true);
        entity.setColor(String.valueOf(button.getTag()));

        enableCalidad2();
    }

    @OnClick({R.id.bt_good, R.id.bt_bad})
    public void onViewClickedOlor(Button button) {
        if (selectedOlor != null) {
            selectedOlor.setSelected(false);
        }
        selectedOlor = button;
        button.setSelected(true);
        entity.setOlor(String.valueOf(button.getTag()));

        enableCalidad2();
    }
}
