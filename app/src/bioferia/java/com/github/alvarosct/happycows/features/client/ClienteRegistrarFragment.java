package com.github.alvarosct.happycows.features.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.db.models.District;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.features.MainMenuActivity;
import com.github.alvarosct.happycows.utils.Constants;
import com.github.alvarosct.happycows.utils.FormManager;
import com.github.alvarosct.happycows.utils.Injector;
import com.github.alvarosct.happycows.utils.views.CustomEditText;
import com.github.alvarosct.happycows.utils.views.CustomSpinner;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ClienteRegistrarFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.et_nombres)
    CustomEditText etNombres;
    @BindView(R.id.et_apellidos)
    CustomEditText etApellidos;
    @BindView(R.id.et_domicilio)
    CustomEditText etDomicilio;
    @BindView(R.id.sp_distrito)
    CustomSpinner spDistrito;
    @BindView(R.id.et_dni)
    CustomEditText etDni;
    @BindView(R.id.et_nac)
    CustomEditText etNac;
    @BindView(R.id.sp_genero)
    CustomSpinner spGenero;
    @BindView(R.id.et_telefono)
    CustomEditText etTelefono;
    @BindView(R.id.et_email)
    CustomEditText etEmail;

    private boolean fromList = false;

    @Override
    public void setupVariables() {
        super.setupVariables();
        fromList = getArguments().getBoolean(Constants.BUNDLE_FROM_LIST, false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cliente_registrar_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupView(View view) {
        super.setupView(view);

        FormManager formManager = new FormManager(getParent());
        formManager.setupEditText(etNombres, true, "", value -> client.setNombres(value));
        formManager.setupEditText(etApellidos, true, "", value -> client.setApellidos(value));
        formManager.setupEditText(etDomicilio, true, "", value -> client.setDomicilio(value));
        formManager.setupCalendarView(etNac, true, "", value -> client.setFechaNac(value));


        List<District> districtList = AppDatabase.getInstance().districtModel().getAll();
        formManager.baseSetupSpinner(spDistrito, true, new ArrayList<>(districtList), 24,
                id -> {
                    client.setDistrito("" + id);
                });


        List<District> genderList = new ArrayList<>();
        genderList.add(new District(0, "Hombre"));
        genderList.add(new District(1, "Mujer"));
        formManager.baseSetupSpinner(spGenero, true, new ArrayList<>(genderList), 0,
                id -> {
                    String gender = id == 0 ? "M" : "F";
                    client.setGenero(gender);
                });

        formManager.setupMandatoryEditText(etEmail, "",
                value -> client.setEmail(value), new FormManager.ICustomError() {
                    @Override
                    public String getError() {
                        return "El correo no es válido";
                    }

                    @Override
                    public boolean isError(String currentText) {
                        return !Patterns.EMAIL_ADDRESS.matcher(currentText).matches();
                    }
                });

        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(8);
        etDni.getEditText().setFilters(fArray);
        etDni.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        formManager.setupMandatoryEditText(etDni, "",
                value -> client.setDni(value), new FormManager.ICustomError() {
                    @Override
                    public String getError() {
                        return "El DNI debe tener 8 dígitos.";
                    }

                    @Override
                    public boolean isError(String currentText) {
                        return currentText.trim().length() != 8;
                    }

                });

        InputFilter[] fArray2 = new InputFilter[1];
        fArray2[0] = new InputFilter.LengthFilter(9);
        etTelefono.getEditText().setFilters(fArray2);
        etTelefono.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        formManager.setupEditText(etTelefono, true, "",
                value -> client.setTelefono(value), new FormManager.ICustomError() {
                    @Override
                    public String getError() {
                        return "El Teléfono debe tener 9 dígitos.";
                    }

                    @Override
                    public boolean isError(String currentText) {
                        return currentText.trim().length() != 9;
                    }

                });

    }

    private Client client = new Client();

    private boolean validar() {
        boolean allValid = etNombres.isValid() && etApellidos.isValid() && etDomicilio.isValid() &&
                spDistrito.isValid() && etDni.isValid() && etNac.isValid() &&
                spGenero.isValid() && etTelefono.isValid() && etEmail.isValid();

        if (!allValid) {
            UtilMethods.showToast("Debe llenar los campos obligatorios");
            return false;
        }
        return true;


    }

    @OnClick(R.id.bt_send)
    public void onBtSendClicked() {

        if (validar()) {
            Injector.provideRepository().registerClient(client, new LoadingCallback<Client>(
                    getContext(), "Registrando cliente...") {
                @Override
                public void onSuccess(boolean fromRemote, Client response) {
                    super.onSuccess(fromRemote, response);

                    new DialogCustom(getContext(),
                            "¡Éxito!", "El cliente ha sido registrado.",
                            new DialogCustom.ButtonBehaviour("Ok", new DialogCustom.IButton() {
                                @Override
                                public void onButtonClick() {
                                    if (fromList) {

                                        Intent intent = new Intent();
                                        intent.putExtra(Constants.RESULT_CLIENT, new Gson().toJson(response, Client.class));
                                        getParent().setResult(Activity.RESULT_OK, intent);
                                        getParent().finish();

                                    } else {
                                        startActivity(new Intent(getParent(), MainMenuActivity.class));
                                        getParent().finish();

                                    }
                                }
                            })).show();
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
