package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class PreguntaInsumo extends BaseModel {

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}