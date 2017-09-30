package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class Porongo extends BaseModel {

    public static final double MIN_ALCOHOL = 60;
    public static final double MAX_ALCOHOL = 90;

    @SerializedName("fecha_hora_entrega")
    private String fechaHoraEntrega;

    @SerializedName("id_ganadero")
    private int ganaderoId;

    @SerializedName("peso")
    private double peso;

    @SerializedName("color")
    private String color;
    @SerializedName("olor")
    private String olor;
    @SerializedName("devolucion")
    private boolean devolucion = false;

    @SerializedName("alcohol")
    private double alcohol;
    @SerializedName("porc_brix")
    private double brix;
    @SerializedName("acidez")
    private int acidez;
    @SerializedName("ph")
    private double ph;
    @SerializedName("densidad")
    private int densidad;

    @SerializedName("limpieza")
    private int limpieza;

    //    ONLY MOBILE
    @SerializedName("accepted")
    private int accepted;


    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int dummy) {
        accepted = dummy;
        if (color.equals("Blanco") && olor.equals("Bueno")) {
            accepted = 1;
        } else {

            if (alcohol > MIN_ALCOHOL && alcohol < MAX_ALCOHOL) {
                accepted = 1;
            } else {
//                TODO: Cambiar Validacion
                if (limpieza == 1) {
                    accepted = 1;
                } else {
                    accepted = 0;
                }
            }

        }
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

    public boolean getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(boolean devolucion) {
        this.devolucion = devolucion;
    }

    public String getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(String fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

}