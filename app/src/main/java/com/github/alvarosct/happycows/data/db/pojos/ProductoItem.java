package com.github.alvarosct.happycows.data.db.pojos;

import android.arch.persistence.room.Entity;

import com.github.alvarosct.happycows.data.db.models.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class ProductoItem extends BaseModel {

    @SerializedName("nombres")
    private String nombres;
    @SerializedName("cantidad")
    private int cantidad;
    @SerializedName("costoUnit")
    private double costoUnit;
    @SerializedName("costoTotal")
    private double costoTotal;
    @SerializedName("unidad")
    private String unidad;

    @SerializedName("lote_id")
    private int loteId = 1;

    public ProductoItem(int id, String nombres, double precio) {
        setId(id);
        this.nombres = nombres;
        setCantidad(0);
        setCostoUnit(precio);
        this.unidad = "Kg";
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
        setCostoTotal(this.costoUnit * this.cantidad);
    }

    public void addCantidad(int diff) {
        int temp = cantidad + diff;
        if (temp >= 0){
            this.cantidad = temp;
            setCostoTotal(this.costoUnit * this.cantidad);
        }
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getCostoUnit() {
        return costoUnit;
    }

    public void setCostoUnit(double costoUnit) {
        this.costoUnit = costoUnit;
        setCostoTotal(this.costoUnit * this.cantidad);
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public int getLoteId() {
        return loteId;
    }

    public void setLoteId(int loteId) {
        this.loteId = loteId;
    }
}