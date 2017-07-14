package com.github.alvarosct.happycows.features.necesidades;

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
import com.github.alvarosct.happycows.features.productos.ProductoSelectActivity;
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

public class NecesidadesRegistrarFragment extends BaseFragment {

    @BindView(R.id.rv_data)
    RecyclerView rvData;

    private NecesidadesRegistrarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.necesidades_registrar_fragment, container, false);
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

        adapter = new NecesidadesRegistrarAdapter(getContext(), new ArrayList<ProductoItem>());
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

        if (requestCode == Constants.INTENT_SELECT_PRODUCT) {
            if (data != null) {
                String productoString = data.getStringExtra(Constants.RESULT_PRODUCTO);
                Producto producto = new Gson().fromJson(productoString, Producto.class);
                adapter.appendRow(producto);
                return;
            }
            UtilMethods.showToast("No es un producto válido");
        }
    }

    @OnClick(R.id.bt_scan)
    public void onBtAddClicked() {
        Intent intent = new Intent(getContext(), ProductoSelectActivity.class);
        startActivityForResult(intent, Constants.INTENT_SELECT_PRODUCT);
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

            Injector.provideRepository().registerNecesidades(ventaFull, new LoadingCallback<JsonObject>(
                    getContext(), "Registrando Necesidades...") {
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
