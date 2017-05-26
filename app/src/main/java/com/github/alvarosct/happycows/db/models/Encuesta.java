package com.github.alvarosct.happycows.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class Encuesta {

    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "ganadero_id")
    private int ganaderoId;

    @ColumnInfo(name = "pregunta_id")
    private int preguntaId;

    @ColumnInfo(name = "valor")
    private int valor;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGanaderoId() {
        return ganaderoId;
    }

    public void setGanaderoId(int ganaderoId) {
        this.ganaderoId = ganaderoId;
    }

    public int getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(int preguntaId) {
        this.preguntaId = preguntaId;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}