package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Pregunta;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface PreguntaDao extends BaseDao<Pregunta> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Pregunta entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Pregunta> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Pregunta entity);

    @Delete
    void delete(Pregunta entity);

    @Query("SELECT * FROM Pregunta")
    List<Pregunta> getAll();

    @Query("SELECT * FROM Pregunta WHERE deletedAt IS NULL AND localChange = 1")
    List<Pregunta> getLocallyChanged();

    @Query("SELECT Count(*) FROM Pregunta WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM Pregunta WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Pregunta WHERE id = :id AND deletedAt IS NULL")
    Pregunta getById(int id);

    @Query("DELETE FROM Pregunta WHERE id = :id")
    void deleteById(int id);
}