package com.rynkbit.dienstplan.db.repository;

import com.rynkbit.dienstplan.entities.Task;

import java.util.List;

/**
 * Created by michael on 12/1/17.
 */

public class TaskRepository implements Repository<Task>{
    @Override
    public void merge(Task object) {

    }

    @Override
    public void remove(Task object) {

    }

    @Override
    public void remove(long id) {

    }

    @Override
    public Task getById(long id) {
        return null;
    }

    @Override
    public List<Task> getAll() {
        return null;
    }
}
