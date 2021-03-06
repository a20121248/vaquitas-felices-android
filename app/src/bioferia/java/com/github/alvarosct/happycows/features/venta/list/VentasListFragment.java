package com.github.alvarosct.happycows.features.venta.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.SimpleDividerItemDecoration;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Venta;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.data.source.remote.RequestManager;
import com.github.alvarosct.happycows.utils.IDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VentasListFragment extends BaseFragment implements IDetail<Venta> {


    @BindView(R.id.rv_data)
    RecyclerView rvData;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ventas_listar_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
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

        listVentas();


    }

    private void listVentas() {
        RequestManager.getWebServices().listVentas().enqueue(new LoadingCallback<List<Venta>>(
                getContext(), "Listando ventas..."
        ) {
            @Override
            public void onSuccess(boolean fromRemote, List<Venta> response) {
                super.onSuccess(fromRemote, response);

                float totalAmount = 0;
                for (Venta venta : response) {
                    totalAmount += venta.getMontoTotal();
                }

                tvNumber.setText(String.valueOf(response.size()));
                tvTotal.setText("S/. " + String.valueOf(totalAmount));

                VentasListAdapter adapter = new VentasListAdapter(
                        VentasListFragment.this, response);
                rvData.setAdapter(adapter);
            }
        });
    }

    @Override
    public void openDetail(Venta obj) {
//        TODO: Open Detail
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
