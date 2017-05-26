package com.github.alvarosct.happycows.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.github.alvarosct.happycows.db.models.Encuesta;
import com.github.alvarosct.happycows.db.models.Pregunta;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface EncuestaDao {
    @Query("SELECT * FROM encuesta")
    List<Encuesta> getAll();

    @Insert
    void insertar(Encuesta porongo);
}