package com.github.alvarosct.happycows.db.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 19/04/2017.
 */

@Entity
public class TableMaster {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    public String getLastSync() {
        return lastSync;
    }

    public void setLastSync(String lastSync) {
        this.lastSync = lastSync;
    }

    private String lastSync;

    public TableMaster(String name) {
        this.name = name;
        this.lastSync = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
