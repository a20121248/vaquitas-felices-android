package com.github.alvarosct.happycows.features.syncDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class SyncDatabaseActivity extends AppCompatActivity {

    private boolean onSync = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        ButterKnife.bind(this);


    }

    @Override
    public void onBackPressed() {
        if (onSync) {
            new DialogCustom(this,
                    "¡Atención!", "¿Estás seguro que cancelar la operación?",
                    new DialogCustom.ButtonBehaviour("Si", new DialogCustom.IButton() {
                        @Override
                        public void onButtonClick() {
                            finish();
                        }
                    }), "No").show();
        }
    }

    private void openMainActivity() {

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SyncDatabaseActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @OnClick(R.id.bt_sync)
    public void onBtSyncClicked() {
        if (onSync) return;

        onSync = true;
        UtilMethods.showDialog("Actualizando...", this);
        new SyncManager(new ISync() {
            @Override
            public void onSyncDone() {
                onSync = false;
                UtilMethods.hideDialog();
                openMainActivity();
            }
        }).startSync();
    }

    @OnClick(R.id.bt_skip)
    public void onBtSkipClicked() {
        openMainActivity();
    }

    public interface ISync {
        void onSyncDone();
    }
}
