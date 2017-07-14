package com.github.alvarosct.happycows.features.materiales;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.SimpleDividerItemDecoration;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.utils.Constants;
import com.github.alvarosct.happycows.utils.IDetail;
import com.github.alvarosct.happycows.utils.Injector;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaterialSelectFragment extends BaseFragment implements IDetail<Insumo> {


    @BindView(R.id.rv_data)
    RecyclerView rvData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.material_listar_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupView(View view) {
        super.setupView(view);
        rvData.setHasFixedSize(true);
        rvData.addItemDecoration(
                new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider_black));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(layoutManager);

        listEntities();
    }

    private void listEntities() {
        Injector.provideRepository().listInsumo(false, new LoadingCallback<List<Insumo>>(
                getContext(), "Obteniendo Materiales...") {
            @Override
            public void onSuccess(boolean fromRemote, List<Insumo> response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().insumoModel().insertAll(response);
                List<Insumo> insumos = AppDatabase.getInstance().insumoModel().listAllUtils();
                MaterialSelectAdapter adapter =
                        new MaterialSelectAdapter(MaterialSelectFragment.this, insumos);
                rvData.setAdapter(adapter);
            }
        });
    }

    @Override
    public void openDetail(Insumo obj) {
        Intent intent = new Intent();
        intent.putExtra(Constants.RESULT_INSUMO, new Gson().toJson(obj, Insumo.class));
        getParent().setResult(Activity.RESULT_OK, intent);
        getParent().finish();
    }
}
