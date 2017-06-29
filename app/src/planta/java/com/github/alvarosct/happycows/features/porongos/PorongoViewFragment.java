package com.github.alvarosct.happycows.features.porongos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.pojos.PorongoFullItem;
import com.github.alvarosct.happycows.utils.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PorongoViewFragment extends BaseFragment {

    @BindView(R.id.tv_ganadero)
    CustomTextView tvGanadero;
    @BindView(R.id.tv_peso)
    CustomTextView tvPeso;
    @BindView(R.id.tv_fecha)
    CustomTextView tvFecha;

    @BindView(R.id.tv_color)
    CustomTextView tvColor;
    @BindView(R.id.tv_olor)
    CustomTextView tvOlor;

    @BindView(R.id.tv_acidez)
    CustomTextView tvAcidez;
    @BindView(R.id.tv_alcohol)
    CustomTextView tvAlcohol;
    @BindView(R.id.tv_densidad)
    CustomTextView tvDensidad;
    @BindView(R.id.tv_brix)
    CustomTextView tvBrix;
    @BindView(R.id.tv_ph)
    CustomTextView tvPh;
    @BindView(R.id.tv_limpieza)
    CustomTextView tvLimpieza;

    Unbinder unbinder;
    protected PorongoFullItem entity;

    public PorongoViewFragment() {
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
        View view = inflater.inflate(R.layout.porongo_display, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupVariables() {
        super.setupVariables();
        entity = AppDatabase.getInstance().porongoModel().getPorongoFull(
                getArguments().getInt(Constants.BUNDLE_ENTITY_ID));
    }

    @Override
    public void setupView(View view) {

        int layout = entity.getPorongo().getAccepted() == 0 ?
                R.id.ly_failure : R.id.ly_success;
        view.findViewById(layout).setVisibility(View.VISIBLE);

        tvGanadero.setText(entity.getNombres());
        tvPeso.setText(String.valueOf(entity.getPorongo().getPeso()));
        tvFecha.setText(UtilMethods.calendarStringToString(
                entity.getPorongo().getFechaHoraEntrega(),
                Constants.BD_DATETIME_FORMAT,
                Constants.DATETIME_FORMAT
        ));

        tvColor.setText(entity.getPorongo().getColor());
        tvOlor.setText(entity.getPorongo().getOlor());

        tvAlcohol.setText(UtilMethods.parseNumber(entity.getPorongo().getAlcohol()));

        tvAcidez.setText(UtilMethods.parseNumber(entity.getPorongo().getAcidez()));
        tvDensidad.setText(UtilMethods.parseNumber(entity.getPorongo().getDensidad()));
        tvBrix.setText(UtilMethods.parseNumber(entity.getPorongo().getBrix()));
        tvPh.setText(UtilMethods.parseNumber(entity.getPorongo().getPh()));
        tvLimpieza.setText(UtilMethods.parseNumber(entity.getPorongo().getLimpieza()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
