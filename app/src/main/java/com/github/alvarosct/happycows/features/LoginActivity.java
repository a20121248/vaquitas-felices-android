package com.github.alvarosct.happycows.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;

import com.github.alvarosct.ascthelper.ui.activities.BaseActivity;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.BuildConfig;
import com.github.alvarosct.happycows.FlavorMethods;
import com.github.alvarosct.happycows.PreferenceManager;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.models.User;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.features.syncDatabase.ISync;
import com.github.alvarosct.happycows.features.syncDatabase.SyncDatabaseActivity;
import com.github.alvarosct.happycows.features.syncDatabase.SyncManager;
import com.github.alvarosct.happycows.utils.Injector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alvarosantacruz on 29/06/17.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_username)
    TextInputLayout etUsername;
    @BindView(R.id.et_password)
    TextInputLayout etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }

    private boolean validate(){
        if (etUsername.getEditText().getText().toString().isEmpty()){
            UtilMethods.showToast(getString(R.string.username_empty));
            return false;
        }
        if (etPassword.getEditText().getText().toString().isEmpty()){
            UtilMethods.showToast(getString(R.string.password_empty));
            return false;
        }

        return true;
    }

    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        if (validate()){
            attemptLogin();
        }
    }

    private void attemptLogin(){
        Injector.provideRepository().loginUser(
                etUsername.getEditText().getText().toString(), etPassword.getEditText().getText().toString(),
                BuildConfig.USER_TYPE, new LoadingCallback<User>(getContext(), "Validando usuario...") {
                    @Override
                    public void onSuccess(boolean fromRemote, User response) {
                        super.onSuccess(fromRemote, response);

                        if (response.getId() == 0) {
                            UtilMethods.showToast("Credenciales inválidas.\nIntente otra vez.");
                            return;
                        }

                        PreferenceManager.getInstance(getContext()).saveUser(response);

                        UtilMethods.showDialog("Actualizando...", getContext());
                        new SyncManager(new ISync() {
                            @Override
                            public void onSyncDone() {
                                UtilMethods.hideDialog();
                                FlavorMethods.openMainActivity(LoginActivity.this);
                            }
                        }).startSync();
                    }
                });
    }
}
