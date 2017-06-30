package com.github.alvarosct.happycows.data.db.pojos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import com.github.alvarosct.happycows.data.db.models.BaseModel;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class PorongoItem extends BaseModel {

    @ColumnInfo(name = "nombres")
    private String nombres;
    @ColumnInfo(name = "peso")
    private int peso;

    @ColumnInfo(name = "accepted")
    private int accepted;
    @ColumnInfo(name = "fechaHoraEntrega")
    private String fechaHoraEntrega;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(String fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }
}