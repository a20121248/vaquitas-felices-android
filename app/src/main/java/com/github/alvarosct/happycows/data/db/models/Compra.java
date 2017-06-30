package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class Compra extends BaseModel {

    @SerializedName("id_proveedor")
    private int idProveedor;
    @SerializedName("monto_total")
    private double montoTotal;
    @SerializedName("fecha_pago")
    private String fechaPago;
    @SerializedName("fecha_programada_entrega")
    private String fechaProgramadaEntrega;

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaProgramadaEntrega() {
        return fechaProgramadaEntrega;
    }

    public void setFechaProgramadaEntrega(String fechaProgramadaEntrega) {
        this.fechaProgramadaEntrega = fechaProgramadaEntrega;
    }
}