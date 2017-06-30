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
    public static final String GANADEROS = "ranchers";
    public static final String PREGUNTAS = "preguntas";

    public static final String USERS = "user";
    public static final String ME = "user/me";
    public static final String ID = "id";



    public static final String INSUMOS = "insumos";
    public static final String PRODUCTOS = "products";
    public static final String VENTAS = "current_sells";
    public static final String SELL = "sell";
    public static final String MATERIALES_USADOS = INSUMOS + "/materiales_usados";
    public static final String NECESIDADES = "needs";
    public static final String DEGUSTACIONES = "degustaciones";
    public static final String CLIENTS = "clients_person";
}
