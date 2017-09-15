package com.github.alvarosct.happycows.features.produccion.detail.insumo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.SimpleDividerItemDecoration;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Ingrediente;
import com.github.alvarosct.happycows.data.db.models.InsumoBoth;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.data.source.remote.RequestManager;
import com.github.alvarosct.happycows.features.produccion.detail.proceso.IngredientesAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class InsumoFormFragment extends BaseFragment {

    protected IngredientesAdapter adapterInsumos;

    @BindView(R.id.rv_data)
    SuperRecyclerView rvData;
    Unbinder unbinder;

    InsumoBoth insumoBoth;

    public InsumoFormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        String entityString = getArguments().getString(Constants.BUNDLE_ENTITY);
        insumoBoth = new Gson().fromJson(entityString, InsumoBoth.class);
        setTitle(insumoBoth.getNombres());
    }

    private void listIngredientes() {

        int insumoId = 0;
        int materiaId = 0;
        if (insumoBoth.isInsumo()) {
            insumoId = insumoBoth.getId();
        } else {
            materiaId = insumoBoth.getId();
        }

        RequestManager.getWebServices().listIngredientesOrden(insumoBoth.getIdOrden(), insumoBoth.getIdProducto(), insumoId, materiaId)
                .enqueue(new LoadingCallback<List<Ingrediente>>(getContext(), "Listando Ingredientes...") {
                    @Override
                    public void onSuccess(boolean fromRemote, List<Ingrediente> response) {
                        super.onSuccess(fromRemote, response);
                        adapterInsumos = new IngredientesAdapter(response, getParent());
                        rvData.setAdapter(adapterInsumos);
                    }
                });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredientes_form_list, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupView(View view) {

        rvData.getRecyclerView().addItemDecoration(
                new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider_black));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(layoutManager);

        listIngredientes();
    }


//    PRESENTER METHODS

    //    Edit for a NEW Form
    public void listEntities() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.bt_done)
    public void onViewClicked() {

//        TODO: Validar

        JsonObject body = new JsonObject();
        body.addProperty("id_orden", insumoBoth.getIdOrden());
        body.add("parametros", adapterInsumos.getJsonArray());
        RequestManager.getWebServices().registrarPasosParametros(body)
                .enqueue(new LoadingCallback<JsonObject>(getContext(), "Registrando...") {
                    @Override
                    public void onSuccess(boolean fromRemote, JsonObject response) {
                        super.onSuccess(fromRemote, response);

                        getParent().setResult(Activity.RESULT_OK);
                        getParent().finish();
                    }
                });

    }
}
