package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.OrdenProduccion;
import com.github.alvarosct.happycows.data.db.pojos.ProduccionItem;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface OrdenProduccionDao extends BaseDao<OrdenProduccion> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(OrdenProduccion entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<OrdenProduccion> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(OrdenProduccion entity);

    @Delete
    void delete(OrdenProduccion entity);

    @Query("SELECT * FROM OrdenProduccion")
    List<OrdenProduccion> getAll();

    @Query("SELECT * FROM OrdenProduccion WHERE deletedAt IS NULL AND localChange = 1")
    List<OrdenProduccion> getLocallyChanged();

    @Query("SELECT Count(*) FROM OrdenProduccion WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM OrdenProduccion WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM OrdenProduccion WHERE id = :id AND deletedAt IS NULL")
    OrdenProduccion getById(int id);

    @Query("DELETE FROM OrdenProduccion WHERE id = :id")
    void deleteById(int id);

//    --------------
//    Custom Queries
//    --------------

    @Query("SELECT P.nombre, OP.cantidadProgramada, OP.id, OP.fechaProgramada " +
            "FROM OrdenProduccion OP INNER JOIN Producto P on P.id = OP.idProducto " +
            "WHERE OP.fechaProduccion IS NULL AND OP.deletedAt IS NULL")
    List<ProduccionItem> listOrdenesProduccion();
}