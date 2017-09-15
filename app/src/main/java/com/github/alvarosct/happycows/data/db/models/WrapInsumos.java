package com.github.alvarosct.happycows.data.db.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alvarosantacruz on 14/07/17.
 */

public class WrapInsumos {

    @SerializedName("materias_primas")
    private List<MateriaPrima> materiasPrimas;

    @SerializedName("insumos")
    private List<Insumo> insumos;

    public List<MateriaPrima> getMateriasPrimas() {
        return materiasPrimas;
    }

    public void setMateriasPrimas(List<MateriaPrima> materiasPrimas) {
        this.materiasPrimas = materiasPrimas;
    }

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<Insumo> insumos) {
        this.insumos = insumos;
    }
}
