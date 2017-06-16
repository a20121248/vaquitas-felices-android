package com.github.alvarosct.happycows.features.syncDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.features.main.MenuBioActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class SyncDatabaseFragment extends BaseFragment {

    private boolean onSync = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sync, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void openMainActivity() {

        getParent().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getParent(), MenuBioActivity.class);
                startActivity(i);
                getParent().finish();
            }
        });
    }

    @OnClick(R.id.bt_sync)
    public void onBtSyncClicked() {
        if (onSync) return;

        onSync = true;
        UtilMethods.showDialog("Actualizando...", getContext());
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

}
