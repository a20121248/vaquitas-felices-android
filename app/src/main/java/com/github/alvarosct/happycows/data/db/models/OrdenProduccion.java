package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class OrdenProduccion extends BaseModel {

    @SerializedName("fecha_programada")
    private String fechaProgramada;
    @SerializedName("fecha_produccion")
    private String fechaProduccion;
    @SerializedName("cantidad_programada")
    private double cantidadProgramada;
    @SerializedName("id_responsable")
    private int idResponsable;
    @SerializedName("id_producto")
    private int idProducto;
    @SerializedName("num_envases")
    private int numEnvases;
    @SerializedName("merma")
    private double merma;
    @SerializedName("observaciones")
    private String observaciones;
    @SerializedName("peso_neto")
    private double pesoNeto;
    @SerializedName("peso_bruto")
    private double pesoBruto;
    @SerializedName("lote")
    private String lote;

    public String getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(String fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(String fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public double getCantidadProgramada() {
        return cantidadProgramada;
    }

    public void setCantidadProgramada(double cantidadProgramada) {
        this.cantidadProgramada = cantidadProgramada;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getNumEnvases() {
        return numEnvases;
    }

    public void setNumEnvases(int numEnvases) {
        this.numEnvases = numEnvases;
    }

    public double getMerma() {
        return merma;
    }

    public void setMerma(double merma) {
        this.merma = merma;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(double pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}