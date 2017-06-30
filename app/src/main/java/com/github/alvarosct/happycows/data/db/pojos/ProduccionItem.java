package com.github.alvarosct.happycows.data.db.pojos;

import android.arch.persistence.room.Entity;
import android.text.TextUtils;

import com.github.alvarosct.happycows.data.db.models.BaseModel;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class ProduccionItem extends BaseModel {

    private String nombre;
    private double cantidadProgramada;
    private String fechaProgramada;

    public double getCantidadProgramada() {
        return cantidadProgramada;
    }

    public void setCantidadProgramada(double cantidadProgramada) {
        this.cantidadProgramada = cantidadProgramada;
    }

    public String getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(String fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}