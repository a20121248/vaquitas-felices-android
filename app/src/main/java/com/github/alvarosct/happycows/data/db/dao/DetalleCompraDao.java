package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.DetalleCompra;
import com.github.alvarosct.happycows.data.db.pojos.DetalleCompraItem;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface DetalleCompraDao extends BaseDao<DetalleCompra> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(DetalleCompra entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<DetalleCompra> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(DetalleCompra entity);

    @Delete
    void delete(DetalleCompra entity);

    @Query("SELECT * FROM DetalleCompra WHERE deletedAt IS NULL")
    List<DetalleCompra> getAll();

    @Query("SELECT * FROM DetalleCompra WHERE deletedAt IS NULL AND localChange = 1")
    List<DetalleCompra> getLocallyChanged();

    @Query("SELECT Count(*) FROM DetalleCompra WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM DetalleCompra WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM DetalleCompra WHERE id = :id AND deletedAt IS NULL")
    DetalleCompra getById(int id);

    @Query("DELETE FROM DetalleCompra WHERE id = :id")
    void deleteById(int id);

//    --------------
//    Custom Queries
//    --------------

    @Query("SELECT I.nombres, DC.idCompra AS compraId, DC.idInsumo, DC.cantidad " +
            "FROM DetalleCompra DC INNER JOIN Insumo I on I.id = DC.idInsumo " +
            "WHERE DC.idCompra = :compraId AND DC.deletedAt IS NULL")
    List<DetalleCompraItem> listDetalleCompra(int compraId);

}