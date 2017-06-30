package com.github.alvarosct.happycows.data.source.remote;

/**
 * Created by Alvaro Santa Cruz on 03/02/2017.
 */

public class Urls {

    public static final String LOGIN = "login";
    public static final String LOGIN_EMPLEADOS = "login_empleados";

    static final String PATH_ID = "ID";
    static final String EXTRA_PATH_ID = "/{" + PATH_ID + "}";

    public static final String PORONGOS = "porongos";
    public static final String DETALLE_CALIDAD = "detalle_calidad";
    public static final String GANADEROS = "ranchers";
    public static final String PREGUNTAS = "preguntas";

    public static final String USERS = "user";
    public static final String ME = "user/me";
    public static final String ID = "id";


    public static final String COMPRA = "compras";
    public static final String DETALLE_COMPRA = "detalle_compra";
    public static final String PARAMETRO_CALIDAD = "parametros_calidad";
    public static final String PROVEEDOR = "proveedores";

    public static final String INSUMOS = "insumos";
    public static final String PRODUCTOS = "products";
    public static final String VENTAS = "current_sells";
    public static final String SELL = "sell";
    public static final String MATERIALES_USADOS = INSUMOS + "/materiales_usados";
    public static final String NECESIDADES = "needs";
    public static final String DEGUSTACIONES = "degustaciones";
    public static final String CLIENTS = "clients_person";



    public static final String ORDEN_PRODUCCION = "orden_produccion";
    public static final String ORDEN_PRODUCCION_FINALIZAR = "orden_produccion/{" + PATH_ID + "}/edit";

    public static final String PROCESOS = "procesos";
    public static final String PASOS = "pasos";
    public static final String ELABORACION_PASOS = "products";

    public static final String INSUMOS_ORDEN = "insumos";
    public static final String INGREDIENTES = "orden_produccion/insumos";
    public static final String ELABORACION_INGREDIENTES = "products";

}
