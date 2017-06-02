package com.github.alvarosct.happycows.data.source.remote;

import com.github.alvarosct.happycows.db.models.Ganadero;
import com.github.alvarosct.happycows.db.models.Porongo;
import com.github.alvarosct.happycows.db.models.Pregunta;
import com.github.alvarosct.happycows.db.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Alvaro Santa Cruz on 03/02/2017.
 */

public interface WebServices {

    @FormUrlEncoded
    @POST(Urls.LOGIN)
    Call<User> login(
            @Field("username") String username,
            @Field("password") String password);

    @POST(Urls.PORONGOS)
    Call<Porongo> createPorongo(@Body Porongo genotypic);

    @PUT(Urls.PORONGOS + Urls.EXTRA_PATH_ID)
    Call<Porongo> updatePorongo(@Path(Urls.PATH_ID) int id,
                                    @Body Porongo genotypic);

    @GET(Urls.USERS)
    Call<List<User>> listUser(@Query("update") String updated);

    @GET(Urls.PORONGOS)
    Call<List<Porongo>> listPorongo(@Query("update") String updated);

    @GET(Urls.GANADEROS)
    Call<List<Ganadero>> listGanadero(@Query("update") String updated);

    @GET(Urls.GANADEROS)
    Call<List<Pregunta>> listPregunta(@Query("update") String updated);

}
