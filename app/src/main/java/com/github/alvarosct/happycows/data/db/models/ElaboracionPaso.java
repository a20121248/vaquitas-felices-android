package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class ElaboracionPaso extends BaseModel {

    @SerializedName("id_pasos")
    private int idPasos;
    @SerializedName("id_orden")
    private int idOrden;
    @SerializedName("valor_parametros")
    private String valorParametros;

    public int getIdPasos() {
        return idPasos;
    }

    public void setIdPasos(int idPasos) {
        this.idPasos = idPasos;
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