package com.github.alvarosct.happycows.data.source.remote;

import com.github.alvarosct.happycows.data.db.models.Client;
import com.github.alvarosct.happycows.data.db.models.Compra;
import com.github.alvarosct.happycows.data.db.models.DetalleCalidad;
import com.github.alvarosct.happycows.data.db.models.DetalleCompra;
import com.github.alvarosct.happycows.data.db.models.Ganadero;
import com.github.alvarosct.happycows.data.db.models.Ingrediente;
import com.github.alvarosct.happycows.data.db.models.Insumo;
import com.github.alvarosct.happycows.data.db.models.OrdenProduccion;
import com.github.alvarosct.happycows.data.db.models.ParametroCalidad;
import com.github.alvarosct.happycows.data.db.models.Paso;
import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.data.db.models.Pregunta;
import com.github.alvarosct.happycows.data.db.models.Proceso;
import com.github.alvarosct.happycows.data.db.models.Producto;
import com.github.alvarosct.happycows.data.db.models.Proveedor;
import com.github.alvarosct.happycows.data.db.models.User;
import com.github.alvarosct.happycows.data.db.models.Venta;
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
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Alvaro Santa Cruz on 03/02/2017.
 */

public interface WebServices {


    @GET(Urls.ORDEN_PRODUCCION)
    Call<List<OrdenProduccion>> listOrdenProduccion();

    @POST(Urls.ORDEN_PRODUCCION_FINALIZAR)
    Call<JsonObject> finalizarOrdenProduccion(@Path(Urls.PATH_ID) int id);

    @GET(Urls.PROCESOS)
    Call<List<Proceso>> listProcesosOrden(@Query("id_producto") int idProducto);

    @GET(Urls.PASOS)
    Call<List<Paso>> listPasosOrden(@Query("id_producto") int idProducto,
                               @Query("id_proceso") int idProceso);

    @GET(Urls.INSUMOS_ORDEN)
    Call<List<Insumo>> listInsumosOrden(@Query("id_producto") int idProducto);

    @GET(Urls.INGREDIENTES)
    Call<List<Ingrediente>> listIngredientesOrden(@Query("id_producto") int idProducto,
                               @Query("id_insumo") int idProceso);


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

    @FormUrlEncoded
    @POST(Urls.DETALLE_CALIDAD)
    Call<DetalleCalidad> updateDetalleCalidad(
            @Field("id_param_calidad") int idParamCalidad,
            @Field("id_compra") int idCompra,
            @Field("id_insumo") int idInsumo,
            @Field("cumple") int cumple);

    @GET(Urls.USERS)
    Call<List<User>> listUser(@Query("update") String updated);

    @GET(Urls.COMPRA)
    Call<List<Compra>> listCompra(@Query("update") String updated);

    @GET(Urls.DETALLE_COMPRA)
    Call<List<DetalleCompra>> listDetalleCompra(@Query("update") String updated);

    @GET(Urls.PARAMETRO_CALIDAD)
    Call<List<ParametroCalidad>> listParametroCalidad(@Query("update") String updated);

    @GET(Urls.PROVEEDOR)
    Call<List<Proveedor>> listProveedor(@Query("update") String updated);

    @GET(Urls.PORONGOS)
    Call<List<Porongo>> listPorongo(@Query("update") String updated);

    @GET(Urls.GANADEROS)
    Call<List<Ganadero>> listGanadero(@Query("update") String updated);

    @GET(Urls.GANADEROS)
    Call<List<Pregunta>> listPregunta(@Query("update") String updated);


    @GET(Urls.INSUMOS)
    Call<List<Insumo>> listInsumo();


    @GET(Urls.PRODUCTOS)
    Call<List<Producto>> listProducto();


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
