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
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.pojos.InsumoItem;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.features.MainMenuActivity;
import com.github.alvarosct.happycows.utils.Constants;
import com.github.alvarosct.happycows.utils.Injector;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterialesRegistrarFragment extends BaseFragment {


    @BindView(R.id.rv_materials)
    RecyclerView rvMaterials;

    private MaterialesRegistrarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.material_registrar_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupView(View view) {
        super.setupView(view);

        rvMaterials.setHasFixedSize(true);
        rvMaterials.addItemDecoration(
                new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider_black));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMaterials.setLayoutManager(layoutManager);

        adapter = new MaterialesRegistrarAdapter(getContext(), new ArrayList<InsumoItem>());
        rvMaterials.setAdapter(adapter);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.INTENT_SELECT_MATERIAL) {
            String insumoString = data.getStringExtra(Constants.RESULT_INSUMO);
            adapter.appendRow(new Gson().fromJson(insumoString, Insumo.class));
        }
    }

    @OnClick(R.id.bt_add)
    public void onBtAddClicked() {
        Intent intent = new Intent(getContext(), MaterialSelectActivity.class);
        startActivityForResult(intent, Constants.INTENT_SELECT_MATERIAL);
    }

    private boolean validar() {

        if (adapter.getItemCount() == 0) {
            UtilMethods.showToast("Debe registrar al menos un material.");
            return false;
        }

        for (InsumoItem insumoItem : adapter.getObjList()) {

            if (insumoItem.getCantidad() == 0) {
                UtilMethods.showToast(
                        String.format("La cantidad de %s debe ser mayor a cero.", insumoItem.getNombres()));
                return false;
            }
        }

        return true;

    }

    @OnClick(R.id.bt_send)
    public void onBtSendClicked() {
        if (validar()) {
            Injector.provideRepository().registerMaterialesUsados(adapter.getObjList(), new LoadingCallback<String>(
                    getContext(), "Registrando materiales...") {
                @Override
                public void onSuccess(boolean fromRemote, String response) {
                    super.onSuccess(fromRemote, response);

                    new DialogCustom(getContext(),
                            "¡Éxito!", response,
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
