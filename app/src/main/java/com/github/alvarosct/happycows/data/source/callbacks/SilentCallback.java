package com.github.alvarosct.happycows.data.source.callbacks;

import android.util.Log;

import com.github.alvarosct.ascthelper.utils.Constants;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public class SilentCallback<T> extends BaseCallback<T> {

    private final String logMessage;

    public SilentCallback(String logMessage) {
        this.logMessage = logMessage;
    }

    @Override
    public void onSuccess(boolean fromRemote, T response) {
        Log.e(Constants.TAG, "SilentCallback - onEveryResponse");
        Log.e(Constants.TAG, String.format("SilentCallback - %s", logMessage));
    }
}
