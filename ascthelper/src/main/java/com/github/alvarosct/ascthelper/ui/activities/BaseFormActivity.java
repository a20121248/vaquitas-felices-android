package com.github.alvarosct.ascthelper.ui.activities;

import android.os.Bundle;

import com.github.alvarosct.ascthelper.ui.fragments.BaseFormFragment;
import com.github.alvarosct.ascthelper.utils.Constants;
import com.google.gson.Gson;


public class BaseFormActivity extends BaseOnePanelChildActivity {

    /**
     * This activity is used to display the CREATE view
     */

    private BaseFormFragment baseFormFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String fragmentString = getIntent().getExtras().getString(Constants.BUNDLE_FORM_FRAGMENT);
        try {
            Class clazz = Class.forName(fragmentString);
            baseFormFragment = (BaseFormFragment) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Bundle bundle = getIntent().getExtras();

//        To handle Screen Orientation Change
        if (savedInstanceState != null) {
            bundle.putAll(savedInstanceState);
        }

        baseFormFragment.setArguments(bundle);
        showFragment(baseFormFragment, true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.BUNDLE_ENTITY, new Gson().toJson(baseFormFragment.getEntity()));
    }

    @Override
    public void onBackPressed() {
        baseFormFragment.closeForm(true);
    }
}
