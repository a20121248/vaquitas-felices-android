package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.User;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

@Dao
public interface UserDao extends BaseDao<User> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<User> entities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(User entity);

    @Delete
    void delete(User entity);

    @Query("SELECT * FROM User WHERE deletedAt = ''")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE deletedAt = '' AND localChange = 1")
    List<User> getLocallyChanged();

    @Query("SELECT Count(*) FROM User WHERE deletedAt = ''")
    int getCountAll();

    @Query("SELECT Count(*) FROM User WHERE deletedAt = '' AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM User WHERE id = :id AND deletedAt = ''")
    User getById(int id);

    @Query("DELETE FROM User WHERE id = :id")
    void deleteById(int id);


//    --------------
//    Custom Queries
//    --------------

    @Query("SELECT * FROM User WHERE username = :username AND password = :password AND deletedAt = '' LIMIT 1")
    User validateUser(String username, String password);
}