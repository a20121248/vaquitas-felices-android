package com.github.alvarosct.happycows.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class Pregunta {

    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}