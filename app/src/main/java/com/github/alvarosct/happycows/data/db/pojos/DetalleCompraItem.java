package com.github.alvarosct.happycows.data.db.pojos;

import android.arch.persistence.room.Entity;

import com.github.alvarosct.happycows.data.db.models.BaseModel;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class DetalleCompraItem extends BaseModel {

    private String nombres;
    private int compraId;
    private int idInsumo;
    private String cantidad;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }
}