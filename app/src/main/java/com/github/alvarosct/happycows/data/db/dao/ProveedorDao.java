package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Proveedor;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface ProveedorDao extends BaseDao<Proveedor> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Proveedor entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Proveedor> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Proveedor entity);

    @Delete
    void delete(Proveedor entity);

    @Query("SELECT * FROM Proveedor WHERE deletedAt IS NULL")
    List<Proveedor> getAll();

    @Query("SELECT * FROM Proveedor WHERE deletedAt IS NULL AND localChange = 1")
    List<Proveedor> getLocallyChanged();

    @Query("SELECT Count(*) FROM Proveedor WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM Proveedor WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Proveedor WHERE id = :id AND deletedAt IS NULL")
    Proveedor getById(int id);

    @Query("DELETE FROM Proveedor WHERE id = :id")
    void deleteById(int id);

//    --------------
//    Custom Queries
//    --------------

}