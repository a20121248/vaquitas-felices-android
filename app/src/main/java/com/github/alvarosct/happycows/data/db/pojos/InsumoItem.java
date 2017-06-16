package com.github.alvarosct.happycows.data.db.pojos;

import android.arch.persistence.room.Entity;

import com.github.alvarosct.happycows.data.db.models.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class InsumoItem extends BaseModel {

    @SerializedName("id")
    private int id;
    @SerializedName("nombres")
    private String nombres;
    @SerializedName("cantidad")
    private int cantidad;
    @SerializedName("unidad")
    private String unidad;

    public InsumoItem(int id, String nombres) {
        this.id = id;
        this.nombres = nombres;
        this.cantidad = 0;
        this.unidad = "Kg";
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getCantidadString() {
        return "" + cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void addCantidad(int diff) {
        int temp = cantidad + diff;
        if (temp >= 0){
            this.cantidad = temp;
        }
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}