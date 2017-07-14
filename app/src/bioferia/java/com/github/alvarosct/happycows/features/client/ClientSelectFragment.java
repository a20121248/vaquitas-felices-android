package com.github.alvarosct.happycows.features.client;

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
import com.github.alvarosct.happycows.R;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.source.callbacks.LoadingCallback;
import com.github.alvarosct.happycows.utils.Constants;
import com.github.alvarosct.happycows.utils.IDetail;
import com.github.alvarosct.happycows.utils.Injector;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ClientSelectFragment extends BaseFragment implements IDetail<Client> {


    @BindView(R.id.rv_data)
    RecyclerView rvData;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.client_listar_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setupView(View view) {
        super.setupView(view);
        rvData.setHasFixedSize(true);
        rvData.addItemDecoration(
                new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider_black));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(layoutManager);

        listEntities();
    }

    private void listEntities() {
        Injector.provideRepository().listClient(true, new LoadingCallback<List<Client>>(
                getContext(), "Obteniendo Clientes...") {
            @Override
            public void onSuccess(boolean fromRemote, List<Client> response) {
                super.onSuccess(fromRemote, response);
                AppDatabase.getInstance().clientModel().insertAll(response);
                List<Client> clients = AppDatabase.getInstance().clientModel().getAll();
                ClientSelectAdapter adapter =
                        new ClientSelectAdapter(ClientSelectFragment.this, clients);
                rvData.setAdapter(adapter);
            }
        });
    }

    @Override
    public void openDetail(Client obj) {
        Intent intent = new Intent();
        intent.putExtra(Constants.RESULT_CLIENT, new Gson().toJson(obj, Client.class));
        getParent().setResult(Activity.RESULT_OK, intent);
        getParent().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 999) {
            getActivity().setResult(Activity.RESULT_OK, data);
            getActivity().finish();
        }
    }

    @OnClick(R.id.bt_send)
    public void onViewClicked() {
        Intent i = new Intent(getContext(), ClienteRegistrarActivity.class);
        i.putExtra(Constants.BUNDLE_FROM_LIST, true);
        startActivityForResult(i, 999);
    }
}
