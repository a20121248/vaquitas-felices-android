package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class Producto extends BaseModel {

    @SerializedName("id_tipo_producto")
    private int idTipoProducto;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("precio_venta")
    private double precioVenta;
    @SerializedName("unidad_medida_venta")
    private String unidadMedidaVenta;
    @SerializedName("unidad_medida_receta")
    private String unidadMedidaReceta;
    @SerializedName("cantidad_referencia_receta")
    private double cantidadReferenciaReceta;
    @SerializedName("dias_vencimiento")
    private int diasVencimiento;
    @SerializedName("activo")
    private boolean activo;
    @SerializedName("stock")
    private int stock;
    @SerializedName("materia_prima")
    private boolean materiaPrima;
    @SerializedName("lote_id")
    private int loteId;

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
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

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getUnidadMedidaVenta() {
        return unidadMedidaVenta;
    }

    public void setUnidadMedidaVenta(String unidadMedidaVenta) {
        this.unidadMedidaVenta = unidadMedidaVenta;
    }

    public String getUnidadMedidaReceta() {
        return unidadMedidaReceta;
    }

    public void setUnidadMedidaReceta(String unidadMedidaReceta) {
        this.unidadMedidaReceta = unidadMedidaReceta;
    }

    public double getCantidadReferenciaReceta() {
        return cantidadReferenciaReceta;
    }

    public void setCantidadReferenciaReceta(double cantidadReferenciaReceta) {
        this.cantidadReferenciaReceta = cantidadReferenciaReceta;
    }

    public int getDiasVencimiento() {
        return diasVencimiento;
    }

    public void setDiasVencimiento(int diasVencimiento) {
        this.diasVencimiento = diasVencimiento;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(boolean materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public int getLoteId() {
        return loteId;
    }

    public void setLoteId(int loteId) {
        this.loteId = loteId;
    }
}