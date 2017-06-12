package com.github.alvarosct.happycows.data.db.models;

import android.arch.persistence.room.Entity;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Entity
public class User extends BaseModel{

    private String names;
    private String surnames;

    private String username;
    private String password;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}