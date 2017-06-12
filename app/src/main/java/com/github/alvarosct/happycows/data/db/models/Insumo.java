package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class Insumo extends BaseModel {

    private String nombre;
    private String tipo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}