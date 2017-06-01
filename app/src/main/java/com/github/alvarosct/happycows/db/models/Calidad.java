package com.github.alvarosct.happycows.db.models;

import android.arch.persistence.room.Entity;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class Calidad extends BaseModel{

    private String color;

    private String olor;

    private String alcohol;

    private String brix;

    private int porongoId;



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOlor() {
        return olor;
    }

    public void setOlor(String olor) {
        this.olor = olor;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getBrix() {
        return brix;
    }

    public void setBrix(String brix) {
        this.brix = brix;
    }

    public int getPorongoId() {
        return porongoId;
    }

    public void setPorongoId(int porongoId) {
        this.porongoId = porongoId;
    }
}