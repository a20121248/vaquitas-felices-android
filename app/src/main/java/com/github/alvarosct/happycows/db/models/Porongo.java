package com.github.alvarosct.happycows.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class Porongo extends BaseModel {


    //    @SerializedName("id_orden_prod")
//    public String id_orden_prod;

    @SerializedName("fecha_hora_ordeno")
    public String fechaHoraOrdeno = "10-10-2017 05:10";


    @SerializedName("id_ganadero")
    private int ganaderoId;

    @SerializedName("peso")
    private double peso;

    @SerializedName("color")
    private String color;
    @SerializedName("olor")
    private String olor;
    @SerializedName("devolucion")
    private int devolucion = 0;

    @SerializedName("alcohol")
    private int alcohol;
    @SerializedName("porc_brix")
    private double brix;
    @SerializedName("acidez")
    public int acidez = 100;
    @SerializedName("ph")
    public double ph = 1.3;
    @SerializedName("densidad")
    public int densidad = 234;
    @SerializedName("limpieza")
    public int limpieza = 3;


    public int getGanaderoId() {
        return ganaderoId;
    }

    public void setGanaderoId(int ganaderoId) {
        this.ganaderoId = ganaderoId;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOlor() {
        return olor;
    }

    public void setOlor(String olor) {
        this.olor = olor;
    }

    public int getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(int alcohol) {
        this.alcohol = alcohol;
    }

    public double getBrix() {
        return brix;
    }

    public void setBrix(double brix) {
        this.brix = brix;
    }

    public int getAcidez() {
        return acidez;
    }

    public void setAcidez(int acidez) {
        this.acidez = acidez;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public int getDensidad() {
        return densidad;
    }

    public void setDensidad(int densidad) {
        this.densidad = densidad;
    }

    public int getLimpieza() {
        return limpieza;
    }

    public void setLimpieza(int limpieza) {
        this.limpieza = limpieza;
    }

    public String getFechaHoraOrdeno() {
        return fechaHoraOrdeno;
    }

    public void setFechaHoraOrdeno(String fechaHoraOrdeno) {
        this.fechaHoraOrdeno = fechaHoraOrdeno;
    }

    public int getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(int devolucion) {
        this.devolucion = devolucion;
    }
}