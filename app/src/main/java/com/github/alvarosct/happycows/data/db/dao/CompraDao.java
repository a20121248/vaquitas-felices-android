package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Compra;
import com.github.alvarosct.happycows.data.db.pojos.CompraItem;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface CompraDao extends BaseDao<Compra> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Compra entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Compra> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Compra entity);

    @Delete
    void delete(Compra entity);

    @Query("SELECT * FROM Compra WHERE deletedAt IS NULL")
    List<Compra> getAll();

    @Query("SELECT * FROM Compra WHERE deletedAt IS NULL AND localChange = 1")
    List<Compra> getLocallyChanged();

    @Query("SELECT Count(*) FROM Compra WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM Compra WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Compra WHERE id = :id AND deletedAt IS NULL")
    Compra getById(int id);

    @Query("DELETE FROM Compra WHERE id = :id")
    void deleteById(int id);

//    --------------
//    Custom Queries
//    --------------

    @Query("SELECT P.razonSocial, P.nombres, P.apellidos, C.id, C.fechaProgramadaEntrega " +
            "FROM Compra C INNER JOIN Proveedor P on P.id = C.idProveedor " +
            "WHERE C.deletedAt IS NULL")
    List<CompraItem> listCompra();

}