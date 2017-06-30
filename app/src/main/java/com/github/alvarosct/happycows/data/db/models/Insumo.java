package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class Insumo extends BaseModel {

    @SerializedName("nombres")
    private String nombres;
    @SerializedName("unidad_medida_stock")
    private String unidadMedidaStock;
    @SerializedName("stock_almacen")
    private int stockAlmacen;
    @SerializedName("stock_planta")
    private int stockPlanta;
    @SerializedName("stock_minimo")
    private int stockMinimo;
    @SerializedName("fecha_baja")
    private String fechaBaja;
    @SerializedName("temp_almac")
    private int tempAlmac;
    @SerializedName("medida_tiempo_vida")
    private String medidaTiempoVida;
    @SerializedName("tiempo_vida")
    private int tiempoVida;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUnidadMedidaStock() {
        return unidadMedidaStock;
    }

    public void setUnidadMedidaStock(String unidadMedidaStock) {
        this.unidadMedidaStock = unidadMedidaStock;
    }

    public int getStockAlmacen() {
        return stockAlmacen;
    }

    public void setStockAlmacen(int stockAlmacen) {
        this.stockAlmacen = stockAlmacen;
    }

    public int getStockPlanta() {
        return stockPlanta;
    }

    public void setStockPlanta(int stockPlanta) {
        this.stockPlanta = stockPlanta;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public int getTempAlmac() {
        return tempAlmac;
    }

    public void setTempAlmac(int tempAlmac) {
        this.tempAlmac = tempAlmac;
    }

    public String getMedidaTiempoVida() {
        return medidaTiempoVida;
    }

    public void setMedidaTiempoVida(String medidaTiempoVida) {
        this.medidaTiempoVida = medidaTiempoVida;
    }

    public int getTiempoVida() {
        return tiempoVida;
    }

    public void setTiempoVida(int tiempoVida) {
        this.tiempoVida = tiempoVida;
    }
}