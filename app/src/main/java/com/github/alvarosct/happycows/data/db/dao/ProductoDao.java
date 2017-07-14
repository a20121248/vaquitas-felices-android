package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Producto;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface ProductoDao extends BaseDao<Producto> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Producto entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Producto> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Producto entity);

    @Delete
    void delete(Producto entity);

    @Query("SELECT * FROM Producto")
    List<Producto> getAll();

    @Query("SELECT * FROM Producto WHERE localChange = 1")
    List<Producto> getLocallyChanged();

    @Query("SELECT Count(*) FROM Producto WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM Producto WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Producto WHERE id = :id")
    Producto getById(int id);

    @Query("DELETE FROM Producto WHERE id = :id")
    void deleteById(int id);





    @Query("SELECT * FROM Producto WHERE id = :id AND precioVenta != 0 AND deletedAt IS NULL")
    Producto getProductoFinal(int id);

    @Query("SELECT * FROM Producto WHERE precioVenta != 0 AND deletedAt IS NULL")
    List<Producto> getProductosFinal();
}