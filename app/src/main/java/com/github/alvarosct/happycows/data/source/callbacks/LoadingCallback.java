package com.github.alvarosct.happycows.data.source.callbacks;

import android.content.Context;

import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.data.source.remote.ApiError;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public abstract class LoadingCallback<T> extends BaseCallback<T>{

    private final Context context;

    public LoadingCallback(Context ctx, String loadingMessage) {
        this.context = ctx;
        UtilMethods.showDialog(loadingMessage, context);
    }

    @Override
    public void onSuccess(boolean fromRemote, T response) {
        UtilMethods.hideDialog();
    }

    @Override
    public void onError(int statusCode, ApiError apiError) {
        super.onError(statusCode, apiError);
        UtilMethods.hideDialog();
    }
}
