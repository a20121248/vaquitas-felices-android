package com.github.alvarosct.ascthelper.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.UtilMethods;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public class BaseFormTabChildFragment<T extends BaseFormTabbedFragment> extends BaseFragment {

    protected T parentFragment;

    public BaseFormTabChildFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void setupVariables() {
        super.setupVariables();
        parentFragment = (T) getParentFragment();
        parentFragment.getFormsManager().setAction(parentFragment.getAction());
    }

    @Override
    public void setupView(View view) {
        super.setupView(view);
        updateViewForAction();
    }

    //    Package Only Access To be override
    void updateViewForAction() {

//        SHOW            -> Disable Fields
//        EDIT/CREATE     -> Enable Fields
        UtilMethods.toggleEnable(getView(),
                !parentFragment.getAction().equals(Constants.ACTION_SHOW));
    }
}