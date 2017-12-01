package com.rynkbit.dienstplan.db.facade;

import java.util.List;

/**
 * Created by michael on 12/1/17.
 */

public interface Facade<T> {
    List<T> getAll();
    T getById(long id);
    void merge(T object);
    void remove(long id);
}
