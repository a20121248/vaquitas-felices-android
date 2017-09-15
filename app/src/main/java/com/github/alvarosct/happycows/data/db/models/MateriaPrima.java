package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class MateriaPrima extends BaseModel {

    @SerializedName("id_tipo_producto")
    private String idTipoProducto;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("precio_venta")
    private String precioVenta;
    @SerializedName("unidad_medida_venta")
    private String unidadMedidaVenta;
    @SerializedName("cantidad_referencia_receta")
    private int cantidadReferenciaReceta;
    @SerializedName("dias_vencimiento")
    private int diasVencimiento;
    @SerializedName("activo")
    private int activo;
    @SerializedName("stock")
    private int stock;
    @SerializedName("materia_prima")
    private int materiaPrima;
    @SerializedName("url")
    private String url;

    public String getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(String idTipoProducto) {
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

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getUnidadMedidaVenta() {
        return unidadMedidaVenta;
    }

    public void setUnidadMedidaVenta(String unidadMedidaVenta) {
        this.unidadMedidaVenta = unidadMedidaVenta;
    }

    public int getCantidadReferenciaReceta() {
        return cantidadReferenciaReceta;
    }

    public void setCantidadReferenciaReceta(int cantidadReferenciaReceta) {
        this.cantidadReferenciaReceta = cantidadReferenciaReceta;
    }

    public int getDiasVencimiento() {
        return diasVencimiento;
    }

    public void setDiasVencimiento(int diasVencimiento) {
        this.diasVencimiento = diasVencimiento;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(int materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}