package com.github.alvarosct.happycows.data.source.remote;

import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.db.models.Ganadero;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.data.db.models.Pregunta;
import com.github.alvarosct.happycows.data.db.models.Producto;
import com.github.alvarosct.happycows.data.db.models.User;
import com.github.alvarosct.happycows.data.db.models.Venta;
import com.github.alvarosct.happycows.data.db.pojos.InsumoItem;
import com.github.alvarosct.happycows.data.db.pojos.InsumoItemWrapper;
import com.github.alvarosct.happycows.data.db.pojos.VentaFull;
import com.google.gson.JsonObject;

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

    @FormUrlEncoded
    @POST(Urls.LOGIN_EMPLEADOS)
    Call<User> loginEmpleados(
            @Field("email") String username,
            @Field("password") String password,
            @Field("tipo_usuario") int userType);

    @POST(Urls.PORONGOS)
    Call<Porongo> createPorongo(@Body Porongo genotypic);

    @FormUrlEncoded
    @POST(Urls.PORONGOS + Urls.EXTRA_PATH_ID)
    Call<Porongo> devolverPorongo(@Path(Urls.PATH_ID) int id,
                                  @Field("devolver") int devolver);

    @GET(Urls.USERS)
    Call<List<User>> listUser(@Query("update") String updated);

    @GET(Urls.PORONGOS)
    Call<List<Porongo>> listPorongo(@Query("update") String updated);

    @GET(Urls.GANADEROS)
    Call<List<Ganadero>> listGanadero(@Query("update") String updated);

    @GET(Urls.GANADEROS)
    Call<List<Pregunta>> listPregunta(@Query("update") String updated);


    @GET(Urls.INSUMOS)
    Call<List<Insumo>> listInsumo();


    @GET(Urls.PRODUCTOS)
    Call<List<Producto>> listProductos();


    @GET(Urls.VENTAS)
    Call<List<Venta>> listVentas();

    @POST(Urls.SELL)
    Call<JsonObject> registerVenta(@Body VentaFull ventaFull);

    @POST(Urls.MATERIALES_USADOS)
    Call<JsonObject> registerMaterialesUsados(@Body InsumoItemWrapper body);

    @POST(Urls.DEGUSTACIONES)
    Call<JsonObject> registerDegustaciones(@Body VentaFull ventaFull);

    @POST(Urls.NECESIDADES)
    Call<JsonObject> registerNecesidades(@Body VentaFull ventaFull);

    @POST(Urls.CLIENTS)
    Call<Client> registerClient(@Body Client client);
}
