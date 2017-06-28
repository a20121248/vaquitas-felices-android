package com.github.alvarosct.happycows.data.db.pojos;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;

import com.github.alvarosct.happycows.data.db.models.BaseModel;
import com.github.alvarosct.happycows.data.db.models.Porongo;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class PorongoFullItem {

    private String nombres;

    @Embedded
    private Porongo porongo;

    public Porongo getPorongo() {
        return porongo;
    }

    public void setPorongo(Porongo porongo) {
        this.porongo = porongo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}