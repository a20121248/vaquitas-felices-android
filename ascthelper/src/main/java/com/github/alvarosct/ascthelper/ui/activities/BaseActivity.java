package com.github.alvarosct.ascthelper.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.github.alvarosct.ascthelper.R;
import com.github.alvarosct.ascthelper.ui.fragments.BaseFormFragment;
import com.github.alvarosct.ascthelper.ui.fragments.BaseFragment;
import com.github.alvarosct.ascthelper.ui.fragments.BaseListFragment;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.dialogs.DialogCustom;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar tbPrimary;
    protected Toolbar tbSecondary;
    protected String action;
    protected BaseFragment fragmentPrimary;
    protected BaseFormFragment fragmentSecondary;
    private boolean twoPaneFlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setAction(getIntent().getStringExtra(Constants.BUNDLE_ACTION));
        twoPaneFlg = false;
    }


    public void closeActivity(){
        finish();
    }

    protected boolean isTwoPaneView() {
        return twoPaneFlg;
    }

    public Context getContext() {
        return this;
    }

    public void showFragment(@NonNull Fragment fragment, boolean primary, boolean addToBackStack, String backStackTag) {

        int fragmentPanelId;
        boolean toolbarPrimary;

        if (primary) {
            fragmentPrimary = (BaseFragment) fragment;
            fragmentPanelId = R.id.panel_primary;
            addToBackStack = false;
            toolbarPrimary = true;
        } else {
            fragmentSecondary = (BaseFormFragment) fragment;
            if (isTwoPaneView()) {
                fragmentPanelId = R.id.panel_secondary;
                addToBackStack = false;
                toolbarPrimary = false;
            } else {
                fragmentPanelId = R.id.panel_primary;
                addToBackStack = true;
                toolbarPrimary = true;
            }
        }

        Bundle bundle = fragment.getArguments() != null ? fragment.getArguments() : new Bundle();
        bundle.putBoolean(Constants.EXTRA_TOOLBAR_PRIMARY, toolbarPrimary);
        fragment.setArguments(bundle);

        FragmentTransaction lft = getSupportFragmentManager().beginTransaction();
//        lft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

        FragmentTransaction ft = lft.replace(fragmentPanelId, fragment);
        if (addToBackStack) ft.addToBackStack(backStackTag);
        ft.commit();
    }

    public void showFragment(@NonNull Fragment fragment, boolean primary) {
        showFragment(fragment, primary, false, "");
    }

    public void removeFragmentSecondary() {
        if (isTwoPaneView()) {
            getSupportFragmentManager().beginTransaction().remove(fragmentSecondary).commit();
        } else {
            getSupportFragmentManager().popBackStack();
        }
        fragmentSecondary = null;
        if (fragmentPrimary instanceof BaseListFragment) {
            ((BaseListFragment) fragmentPrimary).getAdapter().removeSelectedItem();
        }
        refreshToolbar();
    }

    private void refreshToolbar() {
        clearToolbar(tbSecondary);
//        if (tbSecondary != null) tbSecondary.getMenu().clear();
    }

    private void refreshToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.setTitle("");
            toolbar.getMenu().clear();
        }
    }


    private void clearToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.setTitle("");
            toolbar.getMenu().clear();
        }
    }


    @Override
    public void onBackPressed() {
        boolean secondaryFlg = fragmentSecondary != null;
        boolean twoPane = false;

        if (secondaryFlg && !twoPane) {
            fragmentSecondary.closeForm(true);
        } else {
            new DialogCustom(getContext(),
                    "¡Atención!", "¿Estás seguro que deseas salir?",
                    new DialogCustom.ButtonBehaviour("Si", new DialogCustom.IButton() {
                        @Override
                        public void onButtonClick() {
                            finish();
                        }
                    }), "No").show();
        }
    }

    public Toolbar getToolbar(boolean primary) {
        return primary ? tbPrimary : tbSecondary;
    }

    //    TODO: Check Dependencies and then Remove. Unsed.
    public FragmentManager getFM() {
        return getSupportFragmentManager();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
