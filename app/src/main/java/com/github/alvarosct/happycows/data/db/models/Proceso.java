package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class Proceso extends BaseModel {

    @SerializedName("id_maquina")
    private int idMaquina;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("medida_duracion")
    private String medidaDuracion;
    @SerializedName("valor_duracion")
    private int valorDuracion;
    @SerializedName("temp_min")
    private int tempMin;
    @SerializedName("temp_max")
    private int tempMax;

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMedidaDuracion() {
        return medidaDuracion;
    }

    public void setMedidaDuracion(String medidaDuracion) {
        this.medidaDuracion = medidaDuracion;
    }

    public int getValorDuracion() {
        return valorDuracion;
    }

    public void setValorDuracion(int valorDuracion) {
        this.valorDuracion = valorDuracion;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }
}