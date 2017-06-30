package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"idInsumo", "idCompra"})
public class DetalleCompra extends BaseModel {

    @SerializedName("id_insumo")
    private int idInsumo;
    @SerializedName("id_compra")
    private int idCompra;
    @SerializedName("cantidad")
    private int cantidad;
    @SerializedName("precio_total")
    private double precioTotal;
    @SerializedName("cantidad_devuelta")
    private int cantidadDevuelta;
    @SerializedName("monto_devolucion")
    private double montoDevolucion;

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getCantidadDevuelta() {
        return cantidadDevuelta;
    }

    public void setCantidadDevuelta(int cantidadDevuelta) {
        this.cantidadDevuelta = cantidadDevuelta;
    }

    public double getMontoDevolucion() {
        return montoDevolucion;
    }

    public void setMontoDevolucion(double montoDevolucion) {
        this.montoDevolucion = montoDevolucion;
    }
}