package com.github.alvarosct.happycows.data.source.callbacks;

import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.happycows.data.source.remote.ApiError;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public abstract class WrapFallback<T> extends BaseCallback<T> {

    IBaseCallback<T> callback;

    public WrapFallback(IBaseCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(boolean fromRemote, T response) {
        callback.onSuccess(fromRemote, response);
    }

    @Override
    public void onError(int statusCode, ApiError apiError) {
        if (statusCode == Constants.NO_INTERNET){
            onLocalDB();
        } else {
            callback.onError(statusCode, apiError);
        }
    }

    public abstract void onLocalDB();
}
