package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.District;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface DistrictDao extends BaseDao<District> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(District entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<District> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(District entity);

    @Delete
    void delete(District entity);

    @Query("SELECT * FROM District WHERE deletedAt IS NULL")
    List<District> getAll();

    @Query("SELECT * FROM District WHERE deletedAt IS NULL AND localChange = 1")
    List<District> getLocallyChanged();

    @Query("SELECT Count(*) FROM District WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM District WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM District WHERE id = :id AND deletedAt IS NULL")
    District getById(int id);

    @Query("DELETE FROM District WHERE id = :id")
    void deleteById(int id);

//    --------------
//    Custom Queries
//    --------------

}