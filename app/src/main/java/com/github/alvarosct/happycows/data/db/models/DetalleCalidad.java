package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"idParamCalidad", "idCompra", "idInsumo"})
public class DetalleCalidad extends BaseModel {

    @SerializedName("id_param_calidad")
    private int idParamCalidad;
    @SerializedName("id_compra")
    private int idCompra;
    @SerializedName("id_insumo")
    private int idInsumo;
    @SerializedName("cumple")
    private int cumple;

    public int getIdParamCalidad() {
        return idParamCalidad;
    }

    public void setIdParamCalidad(int idParamCalidad) {
        this.idParamCalidad = idParamCalidad;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public int getCumple() {
        return cumple;
    }

    public void setCumple(int cumple) {
        this.cumple = cumple;
    }
}