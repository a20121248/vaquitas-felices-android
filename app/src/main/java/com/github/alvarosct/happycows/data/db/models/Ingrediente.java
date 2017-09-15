package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class Ingrediente extends BaseModel {


    @SerializedName("id_producto_final")
    private int idProductoFinal;
    @SerializedName("id_insumo")
    private String idInsumo;
    @SerializedName("value")
    private String value;
    @SerializedName("id_materia_prima")
    private int idMateriaPrima;
    @SerializedName("reduce_stock")
    private int reduceStock;
    @SerializedName("valor_ideal")
    private double valorIdeal;
    @SerializedName("parametros")
    private String parametros;

    public int getIdProductoFinal() {
        return idProductoFinal;
    }

    public void setIdProductoFinal(int idProductoFinal) {
        this.idProductoFinal = idProductoFinal;
    }

    public String getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(String idInsumo) {
        this.idInsumo = idInsumo;
    }

    public int getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(int idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public int getReduceStock() {
        return reduceStock;
    }

    public void setReduceStock(int reduceStock) {
        this.reduceStock = reduceStock;
    }

    public double getValorIdeal() {
        return valorIdeal;
    }

    public void setValorIdeal(double valorIdeal) {
        this.valorIdeal = valorIdeal;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}