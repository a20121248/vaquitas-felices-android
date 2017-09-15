package com.github.alvarosct.happycows.features.produccion.detail;

import android.app.Activity;
import android.content.Intent;
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
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.models.InsumoBoth;
import com.github.alvarosct.happycows.data.db.models.MateriaPrima;
import com.github.alvarosct.happycows.data.db.models.OrdenProduccion;
import com.github.alvarosct.happycows.data.db.models.Proceso;
import com.github.alvarosct.happycows.data.db.models.WrapInsumos;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.data.source.remote.RequestManager;
import com.google.gson.JsonObject;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrdenProduccionViewFragment extends BaseFragment {

    protected OrdenProduccionProcesosAdapter adapterProcesos;
    protected OrdenProduccionInsumosAdapter adapterInsumos;

    @BindView(R.id.rv_data_insumos)
    SuperRecyclerView rvDataInsumos;
    @BindView(R.id.rv_data_procesos)
    SuperRecyclerView rvDataProcesos;
    Unbinder unbinder;
    private OrdenProduccion ordenProduccion;

    public OrdenProduccionViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        int ordenId = getArguments().getInt(Constants.BUNDLE_ENTITY_ID);
        ordenProduccion = AppDatabase.getInstance().ordenProduccionModel().getById(ordenId);
        setTitle("Orden #" + ordenId);
    }

    public void listInsumos() {

        RequestManager.getWebServices().listInsumosOrden(ordenProduccion.getId(), ordenProduccion.getIdProducto()).enqueue(new LoadingCallback<WrapInsumos>(
                getContext(), "Listando Insumos..."
        ) {
            @Override
            public void onSuccess(boolean fromRemote, WrapInsumos response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().insumoModel().insertAll(response.getInsumos());

                List<InsumoBoth> insumoBoths = new ArrayList<>();
                for (Insumo insumo : response.getInsumos()) {
                    InsumoBoth insumoBoth = new InsumoBoth();
                    insumoBoth.setId(insumo.getId());
                    insumoBoth.setNombres(insumo.getNombres());
                    insumoBoth.setIdProducto(ordenProduccion.getIdProducto());
                    insumoBoth.setIdOrden(ordenProduccion.getId());
                    insumoBoth.setInsumo(true);
                    insumoBoths.add(insumoBoth);
                }
                for (MateriaPrima insumo : response.getMateriasPrimas()) {
                    InsumoBoth insumoBoth = new InsumoBoth();
                    insumoBoth.setId(insumo.getId());
                    insumoBoth.setNombres(insumo.getNombre());
                    insumoBoth.setIdProducto(ordenProduccion.getIdProducto());
                    insumoBoth.setIdOrden(ordenProduccion.getId());
                    insumoBoth.setInsumo(false);
                    insumoBoths.add(insumoBoth);
                }

                adapterInsumos = new OrdenProduccionInsumosAdapter(insumoBoths, getParent());
                rvDataInsumos.setAdapter(adapterInsumos);

                listProcesos();
            }
        });
    }

    public void listProcesos() {

        RequestManager.getWebServices().listProcesosOrden(ordenProduccion.getId(), ordenProduccion.getIdProducto()).enqueue(new LoadingCallback<List<Proceso>>(
                getContext(), "Listando Procesos..."
        ) {
            @Override
            public void onSuccess(boolean fromRemote, List<Proceso> response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().procesoModel().insertAll(response);

                for (Proceso proceso : response) {
                    proceso.setIdProducto(ordenProduccion.getIdProducto());
                    proceso.setIdOrden(ordenProduccion.getId());
                }

                adapterProcesos = new OrdenProduccionProcesosAdapter(response, getParent());
                rvDataProcesos.setAdapter(adapterProcesos);

                listEntities();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orden_produccion_view, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupView(View view) {

        rvDataInsumos.getRecyclerView().addItemDecoration(
                new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider_black));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvDataInsumos.setLayoutManager(layoutManager);

        rvDataProcesos.getRecyclerView().addItemDecoration(
                new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider_black));

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        rvDataProcesos.setLayoutManager(layoutManager2);


        listInsumos();
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

        RequestManager.getWebServices().finalizarOrdenProduccion(ordenProduccion.getIdProducto()).enqueue(new LoadingCallback<JsonObject>(
                getContext(), "Finalizando Orden..."
        ) {
            @Override
            public void onSuccess(boolean fromRemote, JsonObject response) {
                super.onSuccess(fromRemote, response);

                getParent().finish();

            }
        });
    }
}
