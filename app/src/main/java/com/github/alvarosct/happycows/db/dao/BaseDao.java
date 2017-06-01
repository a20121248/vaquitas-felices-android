package com.github.alvarosct.happycows.db.dao;

import java.util.List;

/**
 * Created by Android-Dev on 26/05/2017.
 */

public interface BaseDao<T> {

    long insert(T entity);

    long[] insertAll(List<T> entities);

    void update(T entity);

    void delete(T entity);

    List<T> getAll();

    List<T> getLocallyChanged();

    int getCountAll();

    int getCountChanged();

    T getById(int id);

    void deleteById(int id);

}