package com.github.alvarosct.happycows.features.venta.productos;

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
import com.github.alvarosct.happycows.data.db.models.Producto;
import com.github.alvarosct.happycows.features.materiales.MaterialSelectAdapter;
import com.github.alvarosct.happycows.utils.Constants;
import com.github.alvarosct.happycows.utils.IDetail;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductoSelectFragment extends BaseFragment implements IDetail<Producto> {


    @BindView(R.id.rv_data)
    RecyclerView rvData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.producto_listar_fragment, container, false);
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


        ProductoSelectAdapter adapter = new ProductoSelectAdapter(
                this, AppDatabase.getInstance().productoDao().getAll());
        rvData.setAdapter(adapter);
    }

    @Override
    public void openDetail(Producto obj) {
        Intent intent = new Intent();
        intent.putExtra(Constants.RESULT_QR_CODE, "" + obj.getId());
        getParent().setResult(Activity.RESULT_OK, intent);
        getParent().finish();
    }
}
