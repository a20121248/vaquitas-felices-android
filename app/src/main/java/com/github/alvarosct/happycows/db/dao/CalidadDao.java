package com.github.alvarosct.happycows.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.db.models.Calidad;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface CalidadDao extends BaseDao<Calidad> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Calidad entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Calidad> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Calidad entity);

    @Delete
    void delete(Calidad entity);

    @Query("SELECT * FROM Calidad WHERE status = 1")
    List<Calidad> getAll();

    @Query("SELECT * FROM Calidad WHERE status = 1 AND localChange = 1")
    List<Calidad> getLocallyChanged();

    @Query("SELECT Count(*) FROM Calidad WHERE status = 1")
    int getCountAll();

    @Query("SELECT Count(*) FROM Calidad WHERE status = 1 AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Calidad WHERE id = :id AND status = 1")
    Calidad getById(int id);

    @Query("DELETE FROM Calidad WHERE id = :id")
    void deleteById(int id);
}