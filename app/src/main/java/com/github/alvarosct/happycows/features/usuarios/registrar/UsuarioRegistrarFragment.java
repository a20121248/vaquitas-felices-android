package com.github.alvarosct.happycows.features.usuarios.registrar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.features.main.MenuBioActivity;
import com.github.alvarosct.happycows.utils.Injector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsuarioRegistrarFragment extends BaseFragment {

    @BindView(R.id.til_nombre)
    TextInputLayout tilNombre;
    @BindView(R.id.til_apellidos)
    TextInputLayout tilLastname;
    @BindView(R.id.til_dni)
    TextInputLayout tilDni;
    @BindView(R.id.til_phone)
    TextInputLayout tilPhone;
    @BindView(R.id.til_ref)
    TextInputLayout tilRef;
    @BindView(R.id.til_address)
    TextInputLayout tilAddress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usuario_registrar_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupView(View view) {
        super.setupView(view);

    }

    private boolean validar(Client client) {

        if (TextUtils.isEmpty(client.getName())) {
            UtilMethods.showToast("El nombre no puede estar vacío.");
            return false;
        }

        if (TextUtils.isEmpty(client.getLastname())) {
            UtilMethods.showToast("El apellido no puede estar vacío.");
            return false;
        }

        if (TextUtils.isEmpty(client.getDni())) {
            UtilMethods.showToast("El DNI no puede estar vacío.");
            return false;
        }

        if (client.getDni().length() != 8) {
            UtilMethods.showToast("El DNI debe tener 8 dígitos.");
            return false;
        }

        if (TextUtils.isEmpty(client.getPhone())) {
            UtilMethods.showToast("El teléfono no puede estar vacío.");
            return false;
        }

        if (client.getDni().length() != 8) {
            UtilMethods.showToast("El teléfono debe tener 9 dígitos.");
            return false;
        }

        if (TextUtils.isEmpty(client.getAddress())) {
            UtilMethods.showToast("La dirección no puede estar vacía.");
            return false;
        }

        return true;

    }

    @OnClick(R.id.bt_send)
    public void onBtSendClicked() {

        Client client = new Client();
        client.setName(tilNombre.getEditText().getText().toString().trim());
        client.setLastname(tilLastname.getEditText().getText().toString().trim());
        client.setDni(tilDni.getEditText().getText().toString().trim());
        client.setPhone(tilPhone.getEditText().getText().toString().trim());
        client.setAddress(tilAddress.getEditText().getText().toString().trim());
        client.setReference(tilRef.getEditText().getText().toString().trim());

        if (validar(client)) {
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
                                    startActivity(new Intent(getParent(), MenuBioActivity.class));
                                    getParent().finish();
                                }
                            })).show();
                }
            });
        }
    }

}
