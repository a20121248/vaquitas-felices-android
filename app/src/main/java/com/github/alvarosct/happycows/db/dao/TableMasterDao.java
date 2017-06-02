package com.github.alvarosct.happycows.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.db.models.TableMaster;

import java.util.List;

/**
 * Created by Android-Dev on 19/04/2017.
 */

@Dao
public interface TableMasterDao {

    @Query("SELECT * FROM TableMaster")
    List<TableMaster> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(TableMaster entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<TableMaster> entities);

    @Update
    void update(TableMaster entity);

    @Delete
    void delete(TableMaster entity);

    @Query("DELETE FROM TableMaster WHERE id = :id")
    void deleteById(int id);


//    --------------
//    Custom Queries
//    --------------


    @Query("SELECT lastSync FROM TableMaster ORDER BY lastSync LIMIT 1")
    String getMinimumSync();

    @Query("UPDATE TableMaster SET lastSync = :value WHERE name = :tableName")
    void setLastSyncForTable(String tableName, String value);

    @Query("SELECT lastSync FROM TableMaster WHERE name = :table LIMIT 1")
    String getLastSyncForTable(String table);

}
