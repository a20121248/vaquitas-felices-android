package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.ElaboracionPaso;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface ElaboracionPasoDao extends BaseDao<ElaboracionPaso> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ElaboracionPaso entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<ElaboracionPaso> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ElaboracionPaso entity);

    @Delete
    void delete(ElaboracionPaso entity);

    @Query("SELECT * FROM ElaboracionPaso")
    List<ElaboracionPaso> getAll();

    @Query("SELECT * FROM ElaboracionPaso WHERE deletedAt IS NULL AND localChange = 1")
    List<ElaboracionPaso> getLocallyChanged();

    @Query("SELECT Count(*) FROM ElaboracionPaso WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM ElaboracionPaso WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM ElaboracionPaso WHERE id = :id AND deletedAt IS NULL")
    ElaboracionPaso getById(int id);

    @Query("DELETE FROM ElaboracionPaso WHERE id = :id")
    void deleteById(int id);
}