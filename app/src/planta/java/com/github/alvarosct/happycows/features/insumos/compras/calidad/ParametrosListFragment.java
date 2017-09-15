package com.github.alvarosct.happycows.features.insumos.compras.calidad;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.alvarosct.ascthelper.ui.IAdapterSwitch;
import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.BaseFormManager;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.SimpleDividerItemDecoration;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.DetalleCalidad;
import com.github.alvarosct.happycows.data.db.models.DetalleCompra;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.models.ParametroCalidad;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.data.source.callbacks.SilentCallback;
import com.github.alvarosct.happycows.data.source.remote.ApiError;
import com.github.alvarosct.happycows.data.source.remote.RequestManager;
import com.github.alvarosct.happycows.utils.FormManager;
import com.github.alvarosct.happycows.utils.Injector;
import com.github.alvarosct.happycows.utils.views.CustomEditText;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ParametrosListFragment extends BaseFragment implements IAdapterSwitch {

    protected SuperRecyclerView rvData;
    protected ParametrosAdapter adapter;
    @BindView(R.id.et_cant)
    CustomEditText etCant;
    @BindView(R.id.et_monto)
    CustomEditText etMonto;
    @BindView(R.id.bt_done)
    Button btDone;
    Unbinder unbinder;
    private Insumo insumo;
    private DetalleCompra detalleCompra;
    private int compraId;

    public ParametrosListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        int insumoId = getArguments().getInt(Constants.BUNDLE_ENTITY_ID);
        compraId = getArguments().getInt(Constants.BUNLDE_COMPRA_ID);
        insumo = AppDatabase.getInstance().insumoModel().getById(insumoId);
        detalleCompra = AppDatabase.getInstance().detalleCompraModel().getByIds(insumo.getId(), compraId);

        setTitle("Orden #" + compraId + " - " + insumo.getNombres());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calidad_orden_list, container, false);

        rvData = (SuperRecyclerView) view.findViewById(R.id.rv_data);

        listEntities();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupView(View view) {

        FormManager formManager = new FormManager(getContext());
        formManager.setupMandatoryEditTextNumber(etCant, 0, InputType.TYPE_CLASS_NUMBER,
                value -> {
                    int number = Integer.parseInt(value);
                    if (number > detalleCompra.getCantidad()) {
                        etCant.getEditText().setText("");
                        etCant.getEditText().append(String.valueOf(detalleCompra.getCantidad()));
                    } else {
                        detalleCompra.setCantidadDevuelta(number);
                    }
                });

        formManager.setupMandatoryEditTextNumber(etMonto, 0, InputType.TYPE_CLASS_NUMBER,
                value -> {
                    double number = Double.parseDouble(value);
                    detalleCompra.setMontoDevolucion(number);

//                    if (number > detalleCompra.getPrecioTotal()) {
//                        etMonto.getEditText().setText("");
//                        etMonto.getEditText().append(String.valueOf(detalleCompra.getPrecioTotal()));
//                    } else {
//                        detalleCompra.setMontoDevolucion(number);
//                    }
                });

        rvData.getRecyclerView().setHasFixedSize(true);
        rvData.getRecyclerView().addItemDecoration(
                new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider_black));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(layoutManager);

    }

    @Override
    public void onSwitchChange(int id, int cumple) {

        RequestManager.getWebServices().updateDetalleCalidad(
                id, compraId, insumo.getId(), cumple
        ).enqueue(new SilentCallback<DetalleCalidad>("Updated") {
            @Override
            public void onSuccess(boolean fromRemote, DetalleCalidad response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().detalleCalidadModel().insert(response);
            }
        });
    }

//    PRESENTER METHODS

    //    Edit for a NEW Form
    public void listEntities() {
        Injector.provideRepository().listParametroCalidad(true,
                new LoadingCallback<List<ParametroCalidad>>(getContext(), "Obteniendo registros...") {
            @Override
            public void onSuccess(boolean fromRemote, List<ParametroCalidad> response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().parametroCalidadModel().insertAll(response);
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

        List<ParametroCalidad> entityList = AppDatabase.getInstance()
                .parametroCalidadModel().listByInsumo(insumo.getId());

        List<DetalleCalidad> detalleCalidadList = AppDatabase.getInstance()
                .detalleCalidadModel().listByInsumoCompra(insumo.getId(), compraId);
        adapter = new ParametrosAdapter(entityList, detalleCalidadList, this);
        rvData.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_done)
    public void onViewClicked() {
        int cant = detalleCompra.getCantidad();
        double monto = detalleCompra.getMontoDevolucion();

        RequestManager.getWebServices().postDetalleCompra(insumo.getId(), compraId, cant, monto)
                .enqueue(new LoadingCallback<DetalleCompra>(getContext(), "Registrando") {
                    @Override
                    public void onSuccess(boolean fromRemote, DetalleCompra response) {
                        super.onSuccess(fromRemote, response);
                        AppDatabase.getInstance().detalleCompraModel().insert(response);

                        new DialogCustom(getContext(),
                                "¡Éxito!", "El registro se realizó correctamente.",
                                new DialogCustom.ButtonBehaviour("Ok", new DialogCustom.IButton() {
                                    @Override
                                    public void onButtonClick() {
                                        getParent().setResult(Activity.RESULT_OK);
                                        getParent().closeActivity();
                                    }
                                })).show();
                    }
                });

    }
}

