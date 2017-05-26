package com.github.alvarosct.happycows.db.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class Calidad {

    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "olor")
    private String olor;

    @ColumnInfo(name = "alcohol")
    private String alcohol;

    @ColumnInfo(name = "brix")
    private String brix;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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
}