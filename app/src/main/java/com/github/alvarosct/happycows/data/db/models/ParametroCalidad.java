package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class ParametroCalidad extends BaseModel {

    @SerializedName("id_insumo")
    private int idInsumo;
    @SerializedName("abreviatura")
    private String abreviatura;
    @SerializedName("descripcion")
    private String descripcion;

    private int cumple = 0;

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCumple() {
        return cumple;
    }

    public void switchCumple() {
        cumple = cumple== 0? 1 : 0;
    }

    public void setCumple(int cumple) {
        this.cumple = cumple;
    }
}