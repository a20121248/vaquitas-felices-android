package com.github.alvarosct.happycows.features.insumos.compras.detalle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.ascthelper.R;
import com.github.alvarosct.ascthelper.ui.IAdapterDetail;
import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.SimpleDividerItemDecoration;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.DetalleCompra;
import com.github.alvarosct.happycows.data.db.pojos.DetalleCompraItem;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.data.source.remote.ApiError;
import com.github.alvarosct.happycows.features.insumos.compras.calidad.ParametrosListActivity;
import com.github.alvarosct.happycows.features.porongos.PorongoViewActivity;
import com.github.alvarosct.happycows.utils.Injector;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

public class DetalleCompraListFragment extends BaseFragment implements IAdapterDetail {

    protected SuperRecyclerView rvData;
    protected DetalleCompraAdapter adapter;

    private int compraId;

    public DetalleCompraListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        compraId = getArguments().getInt(Constants.BUNDLE_ENTITY_ID);
        setTitle("Orden de Compra #" + compraId);
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
    public void openDetail(int id) {
        Intent intent = new Intent(getContext(), ParametrosListActivity.class);
        intent.putExtra(Constants.BUNDLE_ENTITY_ID, id);
        intent.putExtra(Constants.BUNLDE_COMPRA_ID, compraId);
        startActivityForResult(intent, Constants.INTENT_FORM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.INTENT_FORM) {
//            DO NOTHING

            listEntitiesLocal();
        }
    }

//    PRESENTER METHODS

    //    Edit for a NEW Form
    public void listEntities() {
        Injector.provideRepository().listDetalleCompra(true,
                new LoadingCallback<List<DetalleCompra>>(getContext(), "Obteniendo registros...") {
            @Override
            public void onSuccess(boolean fromRemote, List<DetalleCompra> response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().detalleCompraModel().insertAll(response);
                listEntitiesLocal();
            }

            @Override
            public void onError(int statusCode, ApiError apiError) {
                super.onError(statusCode, apiError);
                listEntitiesLocal();
            }
        });
    }
    public void listEntitiesLocal() {
        UtilMethods.calendarToString(Constants.BD_DATE_FORMAT);

        List<DetalleCompraItem> entityList = AppDatabase.getInstance()
                .detalleCompraModel().listDetalleCompra(compraId);
        adapter = new DetalleCompraAdapter(entityList, this);
        rvData.setAdapter(adapter);
    }
}
