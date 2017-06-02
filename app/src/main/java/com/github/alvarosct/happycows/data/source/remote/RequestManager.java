package com.github.alvarosct.happycows.data.source.remote;

import com.github.alvarosct.happycows.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alvaro Santa Cruz on 03/02/2017.
 */

public class RequestManager {

    private static WebServices defaultRequestManager;
    private static Retrofit retrofit;


    public static WebServices getWebServices() {
        if (defaultRequestManager == null) {
            Retrofit retrofit = generateRetrofit();
            defaultRequestManager = retrofit.create(WebServices.class);
        }
        return defaultRequestManager;
    }

    private static Retrofit generateRetrofit() {
        Gson gson = new GsonBuilder().create();

        final OkHttpClient client = getClient();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL);

        builder = builder.addConverterFactory(GsonConverterFactory.create(gson));
        retrofit =  builder.client(client).build();
        return retrofit;
    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(12, TimeUnit.SECONDS)
                .connectTimeout(12, TimeUnit.SECONDS);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .method(original.method(), original.body());
                return chain.proceed(requestBuilder.build());
            }
        });

//        builder.addInterceptor(new ConnectivityInterceptor());

        //For adding logs of APIs requests & responses
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addNetworkInterceptor(interceptor);

        return builder.build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
