package com.github.alvarosct.happycows.data.db.models;

import com.github.alvarosct.ascthelper.IBaseModel;

/**
 * Created by alvarosantacruz on 13/07/17.
 */

public class SpinnerModel implements IBaseModel {


    @Override
    public String toString() {
        return "-- Seleccione --";
    }

    @Override
    public int getModelId() {
        return 0;
    }

    @Override
    public void saveModel() {

    }

    @Override
    public boolean isLocalChange() {
        return false;
    }
}


