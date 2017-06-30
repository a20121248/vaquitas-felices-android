package com.github.alvarosct.happycows.data.db.pojos;

import android.arch.persistence.room.Entity;
import android.text.TextUtils;

import com.github.alvarosct.happycows.data.db.models.BaseModel;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class CompraItem extends BaseModel {

    private String razonSocial;
    private String nombres;
    private String apellidos;
    private String fechaProgramadaEntrega;

    public String getName(){
        if (TextUtils.isEmpty(razonSocial)){
            return nombres + " " + apellidos;
        }
        return razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaProgramadaEntrega() {
        return fechaProgramadaEntrega;
    }

    public void setFechaProgramadaEntrega(String fechaProgramadaEntrega) {
        this.fechaProgramadaEntrega = fechaProgramadaEntrega;
    }
}