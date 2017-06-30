package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Proceso;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface ProcesoDao extends BaseDao<Proceso> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Proceso entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Proceso> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Proceso entity);

    @Delete
    void delete(Proceso entity);

    @Query("SELECT * FROM Proceso")
    List<Proceso> getAll();

    @Query("SELECT * FROM Proceso WHERE deletedAt IS NULL AND localChange = 1")
    List<Proceso> getLocallyChanged();

    @Query("SELECT Count(*) FROM Proceso WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM Proceso WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Proceso WHERE id = :id AND deletedAt IS NULL")
    Proceso getById(int id);

    @Query("DELETE FROM Proceso WHERE id = :id")
    void deleteById(int id);
}