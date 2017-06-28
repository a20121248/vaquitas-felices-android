package com.github.alvarosct.happycows.features.ganaderos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.ascthelper.R;
import com.github.alvarosct.ascthelper.ui.IAdapterDetail;
import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.SimpleDividerItemDecoration;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.data.db.AppDatabase;
import com.github.alvarosct.happycows.data.db.models.Ganadero;
import com.github.alvarosct.happycows.data.db.pojos.PorongoItem;
import com.github.alvarosct.happycows.features.porongos.PorongoAdapter;
import com.github.alvarosct.happycows.features.porongos.PorongoViewActivity;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

public class GanaderoSelectFragment extends BaseFragment implements IAdapterDetail {

    protected SuperRecyclerView rvData;
    protected GanaderoAdapter adapter;

    public GanaderoSelectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setTitle("Selecciona el ganadero");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_list, container, false);

        rvData = (SuperRecyclerView) view.findViewById(R.id.rv_data);
        View fab_add = view.findViewById(R.id.fab_add);
//        TODO: Refactor
        fab_add.setVisibility(View.GONE);

        listEntities();
        return view;
    }

    @Override
    public void setupView(View view) {

        rvData.getRecyclerView().setHasFixedSize(true);
        rvData.getRecyclerView().addItemDecoration(
                new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider_black));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(layoutManager);

    }

    @Override
    public void openDetail(int id) {
        Intent intent = new Intent();
        intent.putExtra(Constants.BUNDLE_ENTITY_ID, id);
        getParent().setResult(Activity.RESULT_OK, intent);
        getParent().finish();
    }

//    PRESENTER METHODS

    //    Edit for a NEW Form
    public void listEntities() {
        List<Ganadero> entityList = AppDatabase.getInstance().ganaderoModel().getAll();
        adapter = new GanaderoAdapter(entityList, this);
        rvData.setAdapter(adapter);
    }
}
