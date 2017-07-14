package com.github.alvarosct.ascthelper.utils;

import android.content.Context;

/**
 * Created by Android-Dev on 03/05/2017.
 */


public class BaseFormManager {

    protected int resourceId;
    protected String action;
    protected Context context;

    public BaseFormManager(Context context) {
        this.context = context;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public interface ISpinner {
        void setValueId(int id);
    }

    public interface IEditText {
        void setValue(String value);
    }
}