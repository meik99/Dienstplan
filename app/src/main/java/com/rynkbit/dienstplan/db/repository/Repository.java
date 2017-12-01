package com.rynkbit.dienstplan.db.repository;

import java.util.List;

/**
 * Created by michael on 12/1/17.
 */

public interface Repository<T> {
    void merge(T object);
    void remove(T object);
    void remove(long id);
    T getById(long id);
    List<T> getAll();
}
