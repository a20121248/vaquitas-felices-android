package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class Encuesta extends BaseModel{

    private int ganaderoId;

    private int preguntaId;

    private int valor;

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