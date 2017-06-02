package com.github.alvarosct.happycows.data.source.callbacks;

import com.github.alvarosct.happycows.data.source.remote.ApiError;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public interface IBaseCallback<T> {

    void onSuccess(boolean fromRemote, T response);

    void onError(int statusCode, ApiError apiError);
}
