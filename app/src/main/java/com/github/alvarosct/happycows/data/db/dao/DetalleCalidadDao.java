package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.DetalleCalidad;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface DetalleCalidadDao extends BaseDao<DetalleCalidad> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(DetalleCalidad entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<DetalleCalidad> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(DetalleCalidad entity);

    @Delete
    void delete(DetalleCalidad entity);

    @Query("SELECT * FROM DetalleCalidad WHERE deletedAt IS NULL")
    List<DetalleCalidad> getAll();

    @Query("SELECT * FROM DetalleCalidad WHERE deletedAt IS NULL AND localChange = 1")
    List<DetalleCalidad> getLocallyChanged();

    @Query("SELECT Count(*) FROM DetalleCalidad WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM DetalleCalidad WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM DetalleCalidad WHERE id = :id AND deletedAt IS NULL")
    DetalleCalidad getById(int id);

    @Query("DELETE FROM DetalleCalidad WHERE id = :id")
    void deleteById(int id);

//    --------------
//    Custom Queries
//    --------------

    @Query("SELECT * FROM DetalleCalidad DC " +
            "WHERE DC.idCompra = :compraId AND DC.idInsumo = :insumoId AND DC.cumple=1 AND deletedAt IS NULL")
    List<DetalleCalidad> listByInsumoCompra(int insumoId, int compraId);

}