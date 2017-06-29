package com.github.alvarosct.happycows.features.porongos;

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
import com.github.alvarosct.happycows.data.db.pojos.PorongoFullItem;
import com.github.alvarosct.happycows.data.db.pojos.PorongoItem;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

public class PorongoListFragment extends BaseFragment implements IAdapterDetail {

    protected SuperRecyclerView rvData;
    protected PorongoAdapter adapter;

    public PorongoListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setTitle("Ingresos de leche");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_list, container, false);

        rvData = (SuperRecyclerView) view.findViewById(R.id.rv_data);
        View fab_add = view.findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewView();
            }
        });

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

    public void openNewView() {
        Intent intent = new Intent(getContext(), PorongoFormActivity.class);
        intent.putExtra(Constants.BUNDLE_ENTITY_ID, -1);
        startActivityForResult(intent, Constants.INTENT_FORM);
    }

    @Override
    public void openDetail(int id) {
        Intent intent = new Intent(getContext(), PorongoViewActivity.class);
        intent.putExtra(Constants.BUNDLE_ENTITY_ID, id);
        startActivityForResult(intent, Constants.INTENT_FORM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.INTENT_FORM) {
            listEntities();
        }
    }

//    PRESENTER METHODS

    //    Edit for a NEW Form
    public void listEntities() {
//        TODO: Call WS FIRST
        UtilMethods.calendarToString(Constants.BD_DATE_FORMAT);

        List<PorongoFullItem> entityList = AppDatabase.getInstance().porongoModel()
                .listPorongosToday(UtilMethods.calendarToString(Constants.BD_DATE_FORMAT));
        adapter = new PorongoAdapter(entityList, this);
        rvData.setAdapter(adapter);
    }
}
