package com.github.alvarosct.happycows.data.db.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class InsumoBoth extends BaseModel {

    @SerializedName("nombres")
    private String nombres;

    @SerializedName("id_producto")
    private int idProducto;

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    @SerializedName("id_orden")
    private int idOrden;

    public boolean isInsumo() {
        return insumo;
    }

    public void setInsumo(boolean insumo) {
        this.insumo = insumo;
    }

    @SerializedName("insumo")
    private boolean insumo;


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}