package com.github.alvarosct.happycows.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.github.alvarosct.happycows.db.models.Porongo;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface PorongoDao {
    @Query("SELECT * FROM porongo")
    List<Porongo> getAll();

    @Insert
    void insertar(Porongo porongo);
}