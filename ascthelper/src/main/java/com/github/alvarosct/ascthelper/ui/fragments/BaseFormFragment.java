package com.github.alvarosct.ascthelper.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.alvarosct.ascthelper.IBaseModel;
import com.github.alvarosct.ascthelper.R;
import com.github.alvarosct.ascthelper.utils.BaseFormManager;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public abstract class BaseFormFragment<T extends IBaseModel, F extends BaseFormManager> extends BaseFragment {

    protected T entity;

    protected F formsManager;

    public BaseFormFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void setupVariables() {
        super.setupVariables();
        setAction(getArguments().getString(Constants.BUNDLE_ACTION, ""));
        formsManager = createFormManager();
    }

    @Override
    public void setupView(View view) {
        super.setupView(view);
        updateViewForAction();
    }

    //    Package Only Access To be override
    protected void updateViewForAction() {

//        SHOW            -> Disable Fields
//        EDIT/CREATE     -> Enable Fields
        UtilMethods.toggleEnable(getView(),
                !getAction().equals(Constants.ACTION_SHOW));

        getFormsManager().setAction(getAction());

        getParent().invalidateOptionsMenu();
    }

    public void closeForm(boolean validate) {
        if (validate) {
            validateClosure();
        } else {
            forceClosure();
        }
    }

    private void forceClosure() {
        switch (getAction()) {
            case Constants.ACTION_CREATE:
                getParent().setResult(Activity.RESULT_OK);
                getParent().finish();
                return;
            case Constants.ACTION_EDIT:
                setAction(Constants.ACTION_SHOW);
                updateViewForAction();
                return;
            default:
                UtilMethods.shouldNeverHappen();
        }
    }

    private void validateClosure() {
         switch (getAction()) {
            case Constants.ACTION_CREATE:
            case Constants.ACTION_EDIT:
                new DialogCustom(getContext(),
                        "¡Atención!", "¿Estás seguro que deseas descartar los cambios?",
                        new DialogCustom.ButtonBehaviour("Si", new DialogCustom.IButton() {
                            @Override
                            public void onButtonClick() {
                                forceClosure();
                            }
                        }), "No").show();
                return;
            default:
                getParent().removeFragmentSecondary();
        }
    }

    @Override
    public void onCreateMenu(Menu menu) {
        getToolbar().getMenu().clear();

        if (getAction().equals(Constants.ACTION_SHOW)) {
            getToolbar().setTitle("Detalle - " + getIdentifier());
            getToolbar().inflateMenu(R.menu.menu_show);
            getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    setAction(Constants.ACTION_EDIT);
                    updateViewForAction();
                    return false;
                }
            });
        } else if (getAction().equals(Constants.ACTION_EDIT)) {
            getToolbar().setTitle("Editar - " + getIdentifier());
            getToolbar().inflateMenu(R.menu.menu_register);
            getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    UtilMethods.hideKeyboard(getView(), getContext());
                    saveEntity();
                    return false;
                }
            });
        } else if (getAction().equals(Constants.ACTION_CREATE)) {
            getToolbar().setTitle("Nuevo");
            getToolbar().inflateMenu(R.menu.menu_register);
            getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    UtilMethods.hideKeyboard(getView(), getContext());
                    saveEntity();
                    return false;
                }
            });
        }
    }

    protected void onSaveSuccess() {
        if (getAction().equals(Constants.ACTION_CREATE)) {
        } else {
            setAction(Constants.ACTION_SHOW);
            updateViewForAction();
        }
    }

    public T getEntity() {
        return entity;
    }

    public abstract String getIdentifier();

    protected abstract void saveEntity();

    protected abstract F createFormManager();

    public F getFormsManager() {
        return formsManager;
    }


}