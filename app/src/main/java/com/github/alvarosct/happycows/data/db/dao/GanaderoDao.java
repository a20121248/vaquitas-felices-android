package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Ganadero;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface GanaderoDao extends BaseDao<Ganadero> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Ganadero entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Ganadero> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Ganadero entity);

    @Delete
    void delete(Ganadero entity);

    @Query("SELECT * FROM Ganadero")
    List<Ganadero> getAll();

    @Query("SELECT * FROM Ganadero WHERE deletedAt = '' AND localChange = 1")
    List<Ganadero> getLocallyChanged();

    @Query("SELECT Count(*) FROM Ganadero WHERE deletedAt = ''")
    int getCountAll();

    @Query("SELECT Count(*) FROM Ganadero WHERE deletedAt = '' AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Ganadero WHERE id = :id AND deletedAt = ''")
    Ganadero getById(int id);

    @Query("DELETE FROM Ganadero WHERE id = :id")
    void deleteById(int id);
}