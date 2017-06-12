package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class Ganadero extends BaseModel {

    @SerializedName("nombres")
    private String nombres;
    @SerializedName("apellidos")
    private String apellidos;
    @SerializedName("ult_fecha_insepccion")
    private String ultFechaInsepccion;
    @SerializedName("dni")
    private String dni;
    @SerializedName("genero")
    private String genero;
    @SerializedName("telefono")
    private String telefono;
    @SerializedName("fecha_nac")
    private String fechaNac;
    @SerializedName("resultados_sic")
    private String resultadosSic;
    @SerializedName("id_comunidad")
    private int idComunidad;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUltFechaInsepccion() {
        return ultFechaInsepccion;
    }

    public void setUltFechaInsepccion(String ultFechaInsepccion) {
        this.ultFechaInsepccion = ultFechaInsepccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getResultadosSic() {
        return resultadosSic;
    }

    public void setResultadosSic(String resultadosSic) {
        this.resultadosSic = resultadosSic;
    }

    public int getIdComunidad() {
        return idComunidad;
    }

    public void setIdComunidad(int idComunidad) {
        this.idComunidad = idComunidad;
    }
}