package com.rynkbit.dienstplan.db.facade;

import android.content.Context;

import com.rynkbit.dienstplan.db.repository.TaskRepository;
import com.rynkbit.dienstplan.entities.Task;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 12/1/17.
 */

public class TaskFacade implements Facade<Task> {

    private final Context context;

    public TaskFacade(Context context){
        this.context = context;
    }


    @Override
    public List<Task> getAll() {
        return TaskRepository.getInstance(context).getAll();
    }

    @Override
    public Task getById(long id) {
        return TaskRepository.getInstance(context).getById(id);
    }

    @Override
    public void merge(Task object) {
        if(object != null){
            TaskRepository.getInstance(context).merge(object);
        }
    }

    @Override
    public void remove(long id) {
        TaskRepository.getInstance(context).remove(id);
    }

    public int getNextGroupNumber() {
        List<Task> tasks = TaskRepository.getInstance(context).getAll();
        int maxGroup = 0;

        for(int i = 0; i < tasks.size(); i++){
            maxGroup =
                    tasks.get(i).getGroupNumber() > maxGroup ? tasks.get(i).getGroupNumber() : maxGroup;
        }

        return maxGroup+1;
    }

    public List<Task> getByShiftId(long shiftId) {
        List<Task> result = new LinkedList<>();
        List<Task> tasks = getAll();

        for (Task task : tasks){
            if(task.getShiftId() == shiftId)
                result.add(task);
        }

        return result;
    }

    public List<Task> getByGroupNumber(int group){
        List<Task> result = new LinkedList<>();
        List<Task> tasks = getAll();

        for (Task task : tasks){
            if(task.getGroupNumber() == group)
                result.add(task);
        }

        return result;
    }
}
