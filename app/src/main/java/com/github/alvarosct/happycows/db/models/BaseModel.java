package com.github.alvarosct.happycows.db.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class BaseModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private boolean localChange;

    @SerializedName("created_at")
    private String created;
    @SerializedName("updated_at")
    private String modified;
    @SerializedName("deleted_at")
    private String deletedAt;


    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

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

    public boolean isLocalCreate() {
        return localChange && TextUtils.isEmpty(created);
    }

    public boolean idNull() {
        return id <= 0;
    }

    @Ignore
    public boolean isLocal() {
        return TextUtils.isEmpty(created);
    }

}