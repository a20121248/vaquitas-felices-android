package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.PreguntaInsumo;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface PreguntaInsumoDao extends BaseDao<PreguntaInsumo> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(PreguntaInsumo entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<PreguntaInsumo> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(PreguntaInsumo entity);

    @Delete
    void delete(PreguntaInsumo entity);

    @Query("SELECT * FROM PreguntaInsumo")
    List<PreguntaInsumo> getAll();

    @Query("SELECT * FROM PreguntaInsumo WHERE deletedAt IS NULL AND localChange = 1")
    List<PreguntaInsumo> getLocallyChanged();

    @Query("SELECT Count(*) FROM PreguntaInsumo WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM PreguntaInsumo WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM PreguntaInsumo WHERE id = :id AND deletedAt IS NULL")
    PreguntaInsumo getById(int id);

    @Query("DELETE FROM PreguntaInsumo WHERE id = :id")
    void deleteById(int id);
}