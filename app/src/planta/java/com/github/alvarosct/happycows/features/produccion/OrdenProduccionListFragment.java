package com.github.alvarosct.happycows.features.produccion;

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
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.OrdenProduccion;
import com.github.alvarosct.happycows.data.db.pojos.ProduccionItem;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.data.source.remote.RequestManager;
import com.github.alvarosct.happycows.features.produccion.detail.OrdenProduccionViewActivity;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

public class OrdenProduccionListFragment extends BaseFragment implements IAdapterDetail {

    protected SuperRecyclerView rvData;
    protected OrdenProduccionAdapter adapter;

    public OrdenProduccionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setTitle("Selecciona la orden");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_list, container, false);

        rvData = (SuperRecyclerView) view.findViewById(R.id.rv_data);
        View fab_add = view.findViewById(R.id.fab_add);
        fab_add.setVisibility(View.GONE);

        RequestManager.getWebServices().listOrdenProduccion().enqueue(
                new LoadingCallback<List<OrdenProduccion>>(getContext(), "Listando ordenes de producción...") {
                    @Override
                    public void onSuccess(boolean fromRemote, List<OrdenProduccion> response) {
                        super.onSuccess(fromRemote, response);

                        AppDatabase.getInstance().ordenProduccionModel().insertAll(response);
                        listEntities();
                    }
                });

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
        Intent intent = new Intent(getContext(), OrdenProduccionViewActivity.class);
        intent.putExtra(Constants.BUNDLE_ENTITY_ID, id);
        startActivityForResult(intent, Constants.INTENT_FORM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.INTENT_FORM) {
//            DO NOTHING

//            listEntities();
        }
    }

//    PRESENTER METHODS

    //    Edit for a NEW Form
    public void listEntities() {
        List<ProduccionItem> entityList = AppDatabase.getInstance()
                .ordenProduccionModel().listOrdenesProduccion();
        adapter = new OrdenProduccionAdapter(entityList, this);
        rvData.setAdapter(adapter);
    }
}
