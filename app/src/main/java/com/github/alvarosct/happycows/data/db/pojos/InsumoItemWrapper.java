package com.github.alvarosct.happycows.data.db.pojos;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public class InsumoItemWrapper {

    private List<InsumoItem> insumos;

    public InsumoItemWrapper(List<InsumoItem> insumos) {
        this.insumos = insumos;
    }

    public List<InsumoItem> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<InsumoItem> insumos) {
        this.insumos = insumos;
    }
}