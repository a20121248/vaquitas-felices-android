package com.github.alvarosct.happycows.data.source.callbacks;

import android.util.Log;

import com.github.alvarosct.ascthelper.utils.Constants;
import com.github.alvarosct.ascthelper.utils.UtilMethods;
import com.github.alvarosct.happycows.data.source.remote.ApiError;
import com.github.alvarosct.happycows.data.source.remote.RequestManager;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Alvaro Santa Cruz on 27/02/2017.
 */

public abstract class BaseCallback<T> implements retrofit2.Callback<T>, IBaseCallback<T> {

    private ApiError apiError;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(true, response.body());
        } else {
            try {
//                Special thanks to Ruby Lopez and the OPEAR Team
                Converter<ResponseBody, ApiError> converter = RequestManager.getRetrofit().responseBodyConverter(ApiError.class, new Annotation[0]);
                apiError = converter.convert(response.errorBody());
            } catch (Exception e) {
                apiError = new ApiError("Parse API_ERROR Exception");
            }

            int httpCode = response.code();
            if (httpCode / 100 == 5) {
                Log.e(Constants.TAG, "500 - Internal Server Error");
                onError(Constants.HTTP_INTERNAL_SERVER_ERROR, apiError);
            } else if (httpCode / 100 == 4) {
                Log.e(Constants.TAG, "400 - Bad Request");
                onError(Constants.HTTP_CLIENT_ERROR, apiError);
            } else {
                throw new RuntimeException("ASCT: WS Unknown Status Code");
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
//        TODO: Too general! Implement a ConnectionInterceptor or something like that
        Log.e(Constants.TAG, "No Internet Connection");
        onError(Constants.NO_INTERNET, new ApiError("No Internet Connection"));
    }

    @Override
    public void onError(int statusCode, ApiError apiError) {
        UtilMethods.showToast(apiError.getMessage());
    }
}
