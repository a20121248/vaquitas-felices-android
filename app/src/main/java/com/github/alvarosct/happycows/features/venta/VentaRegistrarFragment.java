package com.github.alvarosct.happycows.features.venta;

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
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Producto;
import com.github.alvarosct.happycows.data.db.pojos.ProductoItem;
import com.github.alvarosct.happycows.data.db.pojos.VentaFull;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.features.main.MenuBioActivity;
import com.github.alvarosct.happycows.features.productos.ProductoSelectActivity;
import com.github.alvarosct.happycows.utils.Constants;
import com.github.alvarosct.happycows.utils.Injector;
import com.google.gson.JsonObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VentaRegistrarFragment extends BaseFragment {


    @BindView(R.id.rv_data)
    RecyclerView rvData;

    private VentaRegistrarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.venta_registrar_fragment, container, false);
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

        adapter = new VentaRegistrarAdapter(getContext(), new ArrayList<ProductoItem>());
        rvData.setAdapter(adapter);
    }

    private void listarProductos() {
        Injector.provideRepository().listProductos(true, new LoadingCallback<List<Producto>>(
                getContext(), "Obteniendo Productos...") {
            @Override
            public void onSuccess(boolean fromRemote, List<Producto> response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().productoDao().insertAll(response);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                int productoId = Integer.parseInt(result.getContents().trim());

                Producto producto = AppDatabase.getInstance().productoDao().getById(productoId);
                adapter.appendRow(producto);
            }
        } else if (resultCode == Activity.RESULT_OK && requestCode == Constants.INTENT_SELECT_MATERIAL) {
            String qrString = data.getStringExtra(Constants.RESULT_QR_CODE);

//            TODO: Procesar QR CODE
            int productoId = Integer.parseInt(qrString.trim());

            Producto producto = AppDatabase.getInstance().productoDao().getById(productoId);
            adapter.appendRow(producto);
        }
    }


    @OnClick(R.id.bt_add)
    public void onBtAddClicked() {
        Intent intent = new Intent(getContext(), ProductoSelectActivity.class);
        startActivityForResult(intent, Constants.INTENT_SELECT_MATERIAL);
    }

    @OnClick(R.id.bt_scan)
    public void onBtScanClicked() {
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(VentaRegistrarFragment.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Escanee el producto");
//        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    private boolean validar() {

        if (adapter.getItemCount() == 0) {
            UtilMethods.showToast("Debe registrar al menos un material.");
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

            VentaFull ventaFull = new VentaFull(1, 1);
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
                                    startActivity(new Intent(getParent(), MenuBioActivity.class));
                                    getParent().finish();
                                }
                            })).show();
                }
            });
        }
    }
}
