package com.github.alvarosct.happycows.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.db.models.Porongo;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface PorongoDao extends BaseDao<Porongo> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Porongo entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Porongo> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Porongo entity);

    @Delete
    void delete(Porongo entity);

    @Query("SELECT * FROM Porongo WHERE deletedAt = ''")
    List<Porongo> getAll();

    @Query("SELECT * FROM Porongo WHERE deletedAt = '' AND localChange = 1")
    List<Porongo> getLocallyChanged();

    @Query("SELECT Count(*) FROM Porongo WHERE deletedAt = ''")
    int getCountAll();

    @Query("SELECT Count(*) FROM Porongo WHERE deletedAt = '' AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Porongo WHERE id = :id AND deletedAt = ''")
    Porongo getById(int id);

    @Query("DELETE FROM Porongo WHERE id = :id")
    void deleteById(int id);
}