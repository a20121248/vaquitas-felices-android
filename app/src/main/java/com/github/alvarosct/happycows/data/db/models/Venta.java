package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity(primaryKeys = {"id"})
public class Venta extends BaseModel {

    @SerializedName("fecha")
    private String fecha;
    @SerializedName("id_vendedor")
    private int idVendedor;
    @SerializedName("id_bioferia")
    private int idBioferia;
    @SerializedName("id_cliente_biof")
    private int idClienteBiof;
    @SerializedName("id_cliente_emp")
    private String idClienteEmp;
    @SerializedName("monto_total")
    private float montoTotal;
    @SerializedName("cliente_emp")
    private Client clienteEmp;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdBioferia() {
        return idBioferia;
    }

    public void setIdBioferia(int idBioferia) {
        this.idBioferia = idBioferia;
    }

    public int getIdClienteBiof() {
        return idClienteBiof;
    }

    public void setIdClienteBiof(int idClienteBiof) {
        this.idClienteBiof = idClienteBiof;
    }

    public String getIdClienteEmp() {
        return idClienteEmp;
    }

    public void setIdClienteEmp(String idClienteEmp) {
        this.idClienteEmp = idClienteEmp;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Client getClienteEmp() {
        return clienteEmp;
    }

    public void setClienteEmp(Client clienteEmp) {
        this.clienteEmp = clienteEmp;
    }
}