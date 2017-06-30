package com.github.alvarosct.happycows.features.produccion.detail;

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
import com.github.alvarosct.happycows.data.db.models.Proceso;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.data.source.remote.RequestManager;
import com.google.gson.JsonObject;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

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
    private int ordenId;

    public OrdenProduccionViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ordenId = getArguments().getInt(Constants.BUNDLE_ENTITY_ID);
        setTitle("Orden #");
    }

    private void listInsumos() {

        RequestManager.getWebServices().listInsumosOrden(ordenId).enqueue(new LoadingCallback<List<Insumo>>(
                getContext(), "Listando Insumos..."
        ) {
            @Override
            public void onSuccess(boolean fromRemote, List<Insumo> response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().insumoModel().insertAll(response);

                adapterInsumos = new OrdenProduccionInsumosAdapter(response, getParent());
                rvDataInsumos.setAdapter(adapterInsumos);

                listProcesos();
            }
        });

    }

    private void listProcesos() {

        RequestManager.getWebServices().listProcesosOrden(ordenId).enqueue(new LoadingCallback<List<Proceso>>(
                getContext(), "Listando Procesos..."
        ) {
            @Override
            public void onSuccess(boolean fromRemote, List<Proceso> response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().procesoModel().insertAll(response);

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

        listInsumos();

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

        RequestManager.getWebServices().finalizarOrdenProduccion(ordenId).enqueue(new LoadingCallback<JsonObject>(
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
