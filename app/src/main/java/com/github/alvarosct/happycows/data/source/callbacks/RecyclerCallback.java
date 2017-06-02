package com.github.alvarosct.happycows.data.source.callbacks;

import android.content.Context;

import com.github.alvarosct.happycows.data.source.remote.ApiError;

import java.util.List;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public abstract class RecyclerCallback<T extends List> extends BaseCallback<T> {

    private Context context;

    public RecyclerCallback(Context ctx) {
        this.context = ctx;
    }

    @Override
    public void onError(int statusCode, ApiError apiError) {

    }
}
