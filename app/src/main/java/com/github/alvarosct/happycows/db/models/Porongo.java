package com.github.alvarosct.happycows.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class Porongo {

    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "familia")
    private String familia;

    @ColumnInfo(name = "numero")
    private int numero;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}