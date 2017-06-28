package com.github.alvarosct.happycows.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alvarosct.happycows.data.db.models.Porongo;
import com.github.alvarosct.happycows.data.db.pojos.PorongoFullItem;
import com.github.alvarosct.happycows.data.db.pojos.PorongoItem;

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

    @Query("SELECT * FROM Porongo WHERE deletedAt IS NULL")
    List<Porongo> getAll();

    @Query("SELECT * FROM Porongo WHERE deletedAt IS NULL AND localChange = 1")
    List<Porongo> getLocallyChanged();

    @Query("SELECT Count(*) FROM Porongo WHERE deletedAt IS NULL")
    int getCountAll();

    @Query("SELECT Count(*) FROM Porongo WHERE deletedAt IS NULL AND localChange = 1")
    int getCountChanged();

    @Query("SELECT * FROM Porongo WHERE id = :id AND deletedAt IS NULL")
    Porongo getById(int id);

    @Query("DELETE FROM Porongo WHERE id = :id")
    void deleteById(int id);


//    --------------
//    Custom Queries
//    --------------


    @Query("SELECT P.id, G.nombres , P.peso , P.devolucion , P.fechaHoraEntrega " +
            "FROM Porongo P INNER JOIN Ganadero G on G.id = P.ganaderoId " +
            "WHERE P.deletedAt IS NULL AND substr(P.fechaHoraEntrega,0,11) = :today")
    List<PorongoItem> listPorongosToday(String today);


    @Query("SELECT  G.nombres , P.* " +
            "FROM Porongo P INNER JOIN Ganadero G on G.id = P.ganaderoId " +
            "WHERE P.deletedAt IS NULL AND P.id = :id")
    PorongoFullItem getPorongoFull(int id);
}