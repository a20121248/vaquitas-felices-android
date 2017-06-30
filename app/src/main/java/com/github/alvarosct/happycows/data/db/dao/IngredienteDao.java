package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Ingrediente;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface IngredienteDao extends BaseDao<Ingrediente> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Ingrediente entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Ingrediente> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Ingrediente entity);

    @Delete
    void delete(Ingrediente entity);

    @Query("SELECT * FROM Ingrediente")
    List<Ingrediente> getAll();

    @Query("SELECT * FROM Ingrediente WHERE deletedAt IS NULL AND localChange = 1")
    List<Ingrediente> getLocallyChanged();

    @Query("SELECT Count(*) FROM Ingrediente WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM Ingrediente WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Ingrediente WHERE id = :id AND deletedAt IS NULL")
    Ingrediente getById(int id);

    @Query("DELETE FROM Ingrediente WHERE id = :id")
    void deleteById(int id);
}