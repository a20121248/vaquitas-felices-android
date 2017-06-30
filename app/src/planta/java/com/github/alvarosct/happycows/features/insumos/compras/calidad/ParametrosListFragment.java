package com.github.alvarosct.happycows.features.insumos.compras.calidad;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.ascthelper.R;
import com.github.alvarosct.ascthelper.ui.IAdapterSwitch;
import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.SimpleDividerItemDecoration;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.DetalleCalidad;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.models.ParametroCalidad;
import com.github.alvarosct.happycows.data.source.callbacks.SilentCallback;
import com.github.alvarosct.happycows.data.source.remote.RequestManager;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

public class ParametrosListFragment extends BaseFragment implements IAdapterSwitch {

    protected SuperRecyclerView rvData;
    protected ParametrosAdapter adapter;
    private Insumo insumo;
    private int compraId;

    public ParametrosListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        int insumoId = getArguments().getInt(Constants.BUNDLE_ENTITY_ID);
        compraId = getArguments().getInt(Constants.BUNLDE_COMPRA_ID);
        insumo = AppDatabase.getInstance().insumoModel().getById(insumoId);

        setTitle("Orden #" + compraId + " - " + insumo.getNombres());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_list, container, false);

        rvData = (SuperRecyclerView) view.findViewById(R.id.rv_data);
        View fab_add = view.findViewById(R.id.fab_add);
        fab_add.setVisibility(View.GONE);

        listEntities();
        return view;
    }

    @Override
    public void setupView(View view) {

        rvData.getRecyclerView().setHasFixedSize(true);
        rvData.getRecyclerView().addItemDecoration(
                new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider_black));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(layoutManager);

    }

    @Override
    public void onSwitchChange(int id, int cumple) {

        RequestManager.getWebServices().updateDetalleCalidad(
                id, compraId, insumo.getId(), cumple
        ).enqueue(new SilentCallback<DetalleCalidad>("Updated") {
            @Override
            public void onSuccess(boolean fromRemote, DetalleCalidad response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().detalleCalidadModel().insert(response);
            }
        });
    }

//    PRESENTER METHODS

    //    Edit for a NEW Form
    public void listEntities() {
//        TODO: Call WS FIRST
        UtilMethods.calendarToString(Constants.BD_DATE_FORMAT);

        List<ParametroCalidad> entityList = AppDatabase.getInstance()
                .parametroCalidadModel().listByInsumo(insumo.getId());

        List<DetalleCalidad> detalleCalidadList = AppDatabase.getInstance()
                .detalleCalidadModel().listByInsumoCompra(insumo.getId(), compraId);
        adapter = new ParametrosAdapter(entityList, detalleCalidadList, this);
        rvData.setAdapter(adapter);
    }
}
