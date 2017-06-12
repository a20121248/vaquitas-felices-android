package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class Porongo extends BaseModel {

    public static final String RED = "Roja";
    public static final String WHITE = "Blanca";
    public static final String YELLOW = "Amarillla";
    public static final String GOOD = "Bueno";
    public static final String BAD = "Malo";


    //    @SerializedName("id_orden_prod")
//    public String id_orden_prod;

    @SerializedName("fecha_hora_ordeno")
    private String fechaHoraOrdeno;


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
    private double alcohol;
    @SerializedName("porc_brix")
    private double brix;
    @SerializedName("acidez")
    private int acidez;
    @SerializedName("ph")
    private double ph ;
    @SerializedName("densidad")
    private int densidad ;

    @SerializedName("limpieza")
    private int limpieza ;

    @SerializedName("step")
    private int step = 0;



    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

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

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
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