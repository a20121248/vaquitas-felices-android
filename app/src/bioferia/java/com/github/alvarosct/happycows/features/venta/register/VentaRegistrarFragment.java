package com.github.alvarosct.happycows.features.venta.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;
import com.github.alvarosct.happycows.PreferenceManager;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.db.models.Producto;
import com.github.alvarosct.happycows.data.db.pojos.ProductoItem;
import com.github.alvarosct.happycows.data.db.pojos.VentaFull;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.data.source.remote.ApiError;
import com.github.alvarosct.happycows.features.MainMenuActivity;
import com.github.alvarosct.happycows.features.client.ClientSelectActivity;
import com.github.alvarosct.happycows.utils.Constants;
import com.github.alvarosct.happycows.utils.Injector;
import com.github.alvarosct.happycows.utils.UtilMethodsCustom;
import com.github.alvarosct.happycows.utils.qr.BarcodeCaptureActivity;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VentaRegistrarFragment extends BaseFragment {


    @BindView(R.id.rv_data)
    RecyclerView rvData;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_client)
    TextView tvClient;

    private VentaRegistrarAdapter adapter;
    private Client client = null;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.venta_registrar_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupView(View view) {
        super.setupView(view);

        listarProductos();

        rvData.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(layoutManager);

        adapter = new VentaRegistrarAdapter(getContext(), new ArrayList<ProductoItem>(),
                new VentaRegistrarAdapter.ITotalize() {
                    @Override
                    public void onTotalChanged(double total) {
                        tvTotal.setText(String.valueOf(total));
                    }
                });
        rvData.setAdapter(adapter);
    }

    private void listarProductos() {
        Injector.provideRepository().listProducto(true, new LoadingCallback<List<Producto>>(
                getContext(), "Obteniendo Productos...") {
            @Override
            public void onSuccess(boolean fromRemote, List<Producto> response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().productoModel().insertAll(response);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) return;

        if (requestCode == Constants.INTENT_QR_READ) {
            if (data != null) {
                Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);

                String productId = UtilMethodsCustom.getProductFromBarcode(barcode.displayValue);
//                    TODO: Procesar QR CODE
                int productoId = Integer.parseInt(productId);

                Producto producto = AppDatabase.getInstance().productoModel().getProductoFinal(productoId);
                String loteId = UtilMethodsCustom.getLoteFromBarcode(barcode.displayValue);

                if (producto != null) {
                    producto.setLoteId(Integer.parseInt(loteId));
                    adapter.appendRow(producto);
                    return;
                }
            }
            UtilMethods.showToast("No es un producto válido");
        }

        if (requestCode == Constants.INTENT_SELECT_CLIENT) {
            String clientString = data.getStringExtra(Constants.RESULT_CLIENT);
            client = new Gson().fromJson(clientString, Client.class);
            tvClient.setText(client.getFullname());
        }
    }

    @OnClick(R.id.bt_scan)
    public void onBtAddClicked() {
        Intent intent = new Intent(getContext(), BarcodeCaptureActivity.class);
        startActivityForResult(intent, Constants.INTENT_QR_READ);
    }

    private boolean validar() {

        if (adapter.getItemCount() == 0) {
            UtilMethods.showToast("Debe registrar al menos un producto.");
            return false;
        }

        for (ProductoItem insumoItem : adapter.getObjList()) {

            if (insumoItem.getCantidad() == 0) {
                UtilMethods.showToast(
                        String.format("La cantidad de %s debe ser mayor a cero.", insumoItem.getNombres()));
                return false;
            }
        }

        return true;

    }

    @OnClick(R.id.bt_done)
    public void onBtNextClicked() {
        if (validar()) {

            int clientId = client == null ? 0 : client.getId();

            VentaFull ventaFull = new VentaFull(1, clientId,
                    PreferenceManager.getInstance(getContext()).getUserInfo().getId());
            ventaFull.setProductoItemList(adapter.getObjList());

            Injector.provideRepository().registerVenta(ventaFull, new LoadingCallback<JsonObject>(
                    getContext(), "Registrando Venta...") {
                @Override
                public void onSuccess(boolean fromRemote, JsonObject response) {
                    super.onSuccess(fromRemote, response);

                    new DialogCustom(getContext(),
                            "¡Éxito!", "La venta se registro correctamente.",
                            new DialogCustom.ButtonBehaviour("Ok", new DialogCustom.IButton() {
                                @Override
                                public void onButtonClick() {
                                    startActivity(new Intent(getParent(), MainMenuActivity.class));
                                    getParent().finish();
                                }
                            })).show();
                }

                @Override
                public void onError(int statusCode, ApiError apiError) {
                    super.onError(statusCode, apiError);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ly_client)
    public void onViewClicked() {
//        Select CLient
        Intent i = new Intent(getContext(), ClientSelectActivity.class);
        startActivityForResult(i, Constants.INTENT_SELECT_CLIENT);
    }
}
