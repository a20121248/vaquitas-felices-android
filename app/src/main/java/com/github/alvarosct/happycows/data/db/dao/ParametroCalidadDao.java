package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.ParametroCalidad;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface ParametroCalidadDao extends BaseDao<ParametroCalidad> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ParametroCalidad entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<ParametroCalidad> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ParametroCalidad entity);

    @Delete
    void delete(ParametroCalidad entity);

    @Query("SELECT * FROM ParametroCalidad WHERE deletedAt IS NULL")
    List<ParametroCalidad> getAll();

    @Query("SELECT * FROM ParametroCalidad WHERE deletedAt IS NULL AND localChange = 1")
    List<ParametroCalidad> getLocallyChanged();

    @Query("SELECT Count(*) FROM ParametroCalidad WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM ParametroCalidad WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM ParametroCalidad WHERE id = :id AND deletedAt IS NULL")
    ParametroCalidad getById(int id);

    @Query("DELETE FROM ParametroCalidad WHERE id = :id")
    void deleteById(int id);

//    --------------
//    Custom Queries
//    --------------

    @Query("SELECT * FROM ParametroCalidad PC " +
            "WHERE PC.idInsumo = :insumoId AND PC.deletedAt IS NULL")
    List<ParametroCalidad> listByInsumo(int insumoId);

}