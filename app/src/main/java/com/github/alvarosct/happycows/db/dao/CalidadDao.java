package com.github.alvarosct.happycows.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.github.alvarosct.happycows.db.models.Calidad;
import com.github.alvarosct.happycows.db.models.Porongo;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface CalidadDao {
    @Query("SELECT * FROM calidad")
    List<Calidad> getAll();

    @Insert
    void insertar(Calidad porongo);
}