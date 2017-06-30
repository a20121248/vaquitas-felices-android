package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class ElaboracionIngrediente extends BaseModel {

    @SerializedName("id_ingredientes")
    private int idIngredientes;
    @SerializedName("id_orden")
    private int idOrden;
    @SerializedName("valor_parametros")
    private String valorParametros;

    public int getIdIngredientes() {
        return idIngredientes;
    }

    public void setIdIngredientes(int idIngredientes) {
        this.idIngredientes = idIngredientes;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getValorParametros() {
        return valorParametros;
    }

    public void setValorParametros(String valorParametros) {
        this.valorParametros = valorParametros;
    }
}