package com.github.alvarosct.happycows.features.degustaciones;

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
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;
import com.github.alvarosct.happycows.PreferenceManager;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Producto;
import com.github.alvarosct.happycows.data.db.pojos.ProductoItem;
import com.github.alvarosct.happycows.data.db.pojos.VentaFull;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.features.MainMenuActivity;
import com.github.alvarosct.happycows.utils.Constants;
import com.github.alvarosct.happycows.utils.Injector;
import com.github.alvarosct.happycows.utils.UtilMethodsCustom;
import com.github.alvarosct.happycows.utils.qr.BarcodeCaptureActivity;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DegustacionesRegistrarFragment extends BaseFragment {

    @BindView(R.id.rv_data)
    RecyclerView rvData;

    private DegustacionesRegistrarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.degustaciones_registrar_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupView(View view) {
        super.setupView(view);

        listarProductos();

        rvData.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(layoutManager);

        adapter = new DegustacionesRegistrarAdapter(getContext(), new ArrayList<ProductoItem>());
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

            VentaFull ventaFull = new VentaFull(1, 1,
                    PreferenceManager.getInstance(getContext()).getUserInfo().getId());
            ventaFull.setProductoItemList(adapter.getObjList());

            Injector.provideRepository().registerDegustaciones(ventaFull, new LoadingCallback<JsonObject>(
                    getContext(), "Registrando Degustaciones...") {
                @Override
                public void onSuccess(boolean fromRemote, JsonObject response) {
                    super.onSuccess(fromRemote, response);

                    new DialogCustom(getContext(),
                            "¡Éxito!", "El registro finalizó correctamente.",
                            new DialogCustom.ButtonBehaviour("Ok", new DialogCustom.IButton() {
                                @Override
                                public void onButtonClick() {
                                    startActivity(new Intent(getParent(), MainMenuActivity.class));
                                    getParent().finish();
                                }
                            })).show();
                }
            });
        }
    }
}
