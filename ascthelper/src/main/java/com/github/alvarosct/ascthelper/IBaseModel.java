package com.github.alvarosct.ascthelper;

/**
 * Created by Android-Dev on 19/05/2017.
 */

public interface IBaseModel {

    public int getModelId();

    public void saveModel();

    public boolean isLocalChange();
}
