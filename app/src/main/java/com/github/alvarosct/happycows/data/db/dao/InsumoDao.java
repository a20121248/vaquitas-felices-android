package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Insumo;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface InsumoDao extends BaseDao<Insumo> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Insumo entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Insumo> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Insumo entity);

    @Delete
    void delete(Insumo entity);

    @Query("SELECT * FROM Insumo")
    List<Insumo> getAll();

    @Query("SELECT * FROM Insumo WHERE deletedAt = '' AND localChange = 1")
    List<Insumo> getLocallyChanged();

    @Query("SELECT Count(*) FROM Insumo WHERE deletedAt = ''")
    int getCountAll();

    @Query("SELECT Count(*) FROM Insumo WHERE deletedAt = '' AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Insumo WHERE id = :id AND deletedAt = ''")
    Insumo getById(int id);

    @Query("DELETE FROM Insumo WHERE id = :id")
    void deleteById(int id);
}