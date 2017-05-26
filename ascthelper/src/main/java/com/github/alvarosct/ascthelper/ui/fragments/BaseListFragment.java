package com.github.alvarosct.ascthelper.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.alvarosct.ascthelper.IBaseModel;
import com.github.alvarosct.ascthelper.R;
import com.github.alvarosct.ascthelper.ui.BaseListAdapter;
import com.github.alvarosct.ascthelper.ui.IAdapterDetail;
import com.github.alvarosct.ascthelper.ui.activities.BaseFormActivity;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.SimpleDividerItemDecoration;
import com.google.gson.Gson;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

public abstract class BaseListFragment<T extends IBaseModel> extends BaseFragment implements IAdapterDetail {

    protected SuperRecyclerView rvData;
    protected BaseListAdapter adapter;

    /**
     * Dear future developer....
     * Here is a piece of advise for a future maintenance
     * <p>
     * Steps to adapt this view to a NEW form:
     * 1. Change the values from the following variables: EXTRA_FRAGMENT_CLASS, LAYOUT_ID
     * 2. Change the @displayEntity method.
     * 3. Change the presenter methods to suite your needs.
     */

//    Edit for a NEW Form
    public BaseListFragment() {
        // Required empty public constructor
    }

    public abstract String getTitle();

    public abstract Class getFragmentFormClass();

    public abstract int getEntitySelectionId(T entity);

    public abstract Class<T> getEntityClass();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    private T getEntityFromString(String entityString) {
        return new Gson().fromJson(entityString, getEntityClass());
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
        if (getArguments() != null && getArguments().getBoolean(Constants.BUNDLE_SECOND_PANEL, false)) {
            openDetailView(getEntityFromString(getArguments().getString(Constants.BUNDLE_ENTITY)));
        } else {
            autoOpenDetail();
        }

        getArguments().clear();

        return view;
    }

    private void autoOpenDetail(){
        if (isTwoPaneView()) {
//            Show the first Item if nothing selected. Only for TwoPane View
            openDetail(adapter.getObjectFirstSelectionId());
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreateMenu(Menu menu) {
        getToolbar().getMenu().clear();
        getToolbar().inflateMenu(R.menu.default_menu_left);
        getToolbar().setTitle(getTitle());
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                openFilter();
                return false;
            }
        });
    }

    @Override
    public void setupView(View view) {

        rvData.getRecyclerView().setHasFixedSize(true);
        rvData.getRecyclerView().addItemDecoration(
                new SimpleDividerItemDecoration(getContext(), R.drawable.line_divider_black));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvData.setLayoutManager(layoutManager);

//        Add on click Listener

    }

    public void openNewView() {
        Intent i = new Intent(getContext(), BaseFormActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_ENTITY, "");
        bundle.putString(Constants.BUNDLE_ACTION, Constants.ACTION_CREATE);
        bundle.putString(Constants.BUNDLE_FORM_FRAGMENT, getFragmentFormClass().getName());
        i.putExtras(bundle);
        startActivityForResult(i, Constants.INTENT_FORM);
    }

    @Override
    public void openDetail(int id) {
        T entity = setEntity(id);
        openDetailView(entity);
    }

    public abstract T setEntity(int id);

    public void openDetailView(T entity) {

        adapter.selectItemSelected(getEntitySelectionId(entity));

        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_ENTITY, new Gson().toJson(entity));
        bundle.putString(Constants.BUNDLE_ACTION, Constants.ACTION_SHOW);
        bundle.putString(Constants.BUNDLE_FORM_FRAGMENT, getFragmentFormClass().getName());

        try {

            Fragment fragment = (BaseFormFragment) getFragmentFormClass().newInstance();
            fragment.setArguments(bundle);
            getParent().showFragment(fragment, false);

        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.INTENT_FORM) {
            listEntities();
            autoOpenDetail();
        }
    }

    public BaseListAdapter getAdapter() {
        return adapter;
    }

//    PRESENTER METHODS

    //    Edit for a NEW Form
    public abstract void listEntities();

    public abstract void openFilter();
}
