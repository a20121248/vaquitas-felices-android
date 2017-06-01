package com.github.alvarosct.happycows.db.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class BaseModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private boolean localChange;

    private int status = 1;


    public int getId() {
        return id;
    }

    public void setId(int uid) {
        this.id = uid;
    }

    public boolean isLocalChange() {
        return localChange;
    }

    public void setLocalChange(boolean localChange) {
        this.localChange = localChange;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}