package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Encuesta;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface EncuestaDao extends BaseDao<Encuesta> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Encuesta entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Encuesta> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Encuesta entity);

    @Delete
    void delete(Encuesta entity);

    @Query("SELECT * FROM Encuesta WHERE deletedAt IS NULL")
    List<Encuesta> getAll();

    @Query("SELECT * FROM Encuesta WHERE deletedAt IS NULL AND localChange = 1")
    List<Encuesta> getLocallyChanged();

    @Query("SELECT Count(*) FROM Encuesta WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM Encuesta WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Encuesta WHERE id = :id AND deletedAt IS NULL")
    Encuesta getById(int id);

    @Query("DELETE FROM Encuesta WHERE id = :id")
    void deleteById(int id);
}