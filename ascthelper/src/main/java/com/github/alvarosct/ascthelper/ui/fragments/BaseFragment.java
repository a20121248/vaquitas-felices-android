package com.github.alvarosct.ascthelper.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.github.alvarosct.ascthelper.R;
import com.github.alvarosct.ascthelper.ui.activities.BaseActivity;
import com.github.alvarosct.ascthelper.utils.Constants;


public class BaseFragment extends Fragment {

    private BaseActivity parent;
    private Toolbar toolbar;
    private boolean twoPaneFlg;

    private String title = "";

    public BaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = (BaseActivity) getActivity();
        twoPaneFlg = false;
        setupVariables();
    }

    protected boolean isTwoPaneView(){
        return twoPaneFlg;
    }

    public void setupVariables() {
        toolbar = parent.getToolbar(true);
//        toolbar = parent.getToolbar(getArguments() != null &&
//                getArguments().getBoolean(Constants.EXTRA_TOOLBAR_PRIMARY));
    }

    public void setupView(View view) {

    }

    public void setTitle(String title){
        this.title = title;
    }

    public void onCreateMenu(Menu menu) {
        getToolbar().setTitle(title);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    @Override
    public final void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        onCreateMenu(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public BaseActivity getParent() {
        return parent;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbarTitle(String title) {
        getToolbar().setTitle(title);
    }

    public String getAction() {
        return this.getParent().getAction();
    }

    public void setAction(String action) {
        this.getParent().setAction(action);
    }
}
