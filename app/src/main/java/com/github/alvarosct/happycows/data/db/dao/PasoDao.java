package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Paso;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface PasoDao extends BaseDao<Paso> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Paso entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Paso> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Paso entity);

    @Delete
    void delete(Paso entity);

    @Query("SELECT * FROM Paso")
    List<Paso> getAll();

    @Query("SELECT * FROM Paso WHERE deletedAt IS NULL AND localChange = 1")
    List<Paso> getLocallyChanged();

    @Query("SELECT Count(*) FROM Paso WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM Paso WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Paso WHERE id = :id AND deletedAt IS NULL")
    Paso getById(int id);

    @Query("DELETE FROM Paso WHERE id = :id")
    void deleteById(int id);
}