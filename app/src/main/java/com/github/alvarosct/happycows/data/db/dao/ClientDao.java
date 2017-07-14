package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Client;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface ClientDao extends BaseDao<Client> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Client entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Client> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Client entity);

    @Delete
    void delete(Client entity);

    @Query("SELECT * FROM Client WHERE deletedAt IS NULL")
    List<Client> getAll();

    @Query("SELECT * FROM Client WHERE deletedAt IS NULL AND localChange = 1")
    List<Client> getLocallyChanged();

    @Query("SELECT Count(*) FROM Client WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM Client WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Client WHERE id = :id AND deletedAt IS NULL")
    Client getById(int id);

    @Query("DELETE FROM Client WHERE id = :id")
    void deleteById(int id);

//    --------------
//    Custom Queries
//    --------------

}