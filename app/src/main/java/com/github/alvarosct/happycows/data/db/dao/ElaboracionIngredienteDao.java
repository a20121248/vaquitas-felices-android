package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.ElaboracionIngrediente;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface ElaboracionIngredienteDao extends BaseDao<ElaboracionIngrediente> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ElaboracionIngrediente entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<ElaboracionIngrediente> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ElaboracionIngrediente entity);

    @Delete
    void delete(ElaboracionIngrediente entity);

    @Query("SELECT * FROM ElaboracionIngrediente")
    List<ElaboracionIngrediente> getAll();

    @Query("SELECT * FROM ElaboracionIngrediente WHERE deletedAt IS NULL AND localChange = 1")
    List<ElaboracionIngrediente> getLocallyChanged();

    @Query("SELECT Count(*) FROM ElaboracionIngrediente WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM ElaboracionIngrediente WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM ElaboracionIngrediente WHERE id = :id AND deletedAt IS NULL")
    ElaboracionIngrediente getById(int id);

    @Query("DELETE FROM ElaboracionIngrediente WHERE id = :id")
    void deleteById(int id);
}