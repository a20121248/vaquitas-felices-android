package com.github.alvarosct.happycows.features.porongos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Ganadero;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.data.db.pojos.PorongoFullItem;
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

    protected Porongo entity;

    public PorongoFormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Porongo #");
        setHasOptionsMenu(true);
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

    private void setGanaderoNombre(){

        String ganaderoString = "Seleccionar";
        if (entity.getGanaderoId() != 0){
            ganaderoString = AppDatabase.getInstance().ganaderoModel().
                    getById(entity.getGanaderoId()).getNombres();
        }

        tvGanadero.setText(ganaderoString);

    }

    @Override
    public void setupView(View view) {

        setGanaderoNombre();
        etPeso.setText(String.valueOf(entity.getPeso()));


//        tvColor.setText(entity.getPorongo().getColor());
//        tvOlor.setText(entity.getPorongo().getOlor());
//
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
        if (resultCode == Activity.RESULT_OK){
            if (requestCode ==  Constants.INTENT_SELECT_GANADERO){
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
}
