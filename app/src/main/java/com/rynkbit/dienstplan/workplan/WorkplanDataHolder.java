package com.rynkbit.dienstplan.workplan;

import android.app.Application;

import com.rynkbit.dienstplan.entities.Task;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 12/1/17.
 */

public class WorkplanDataHolder extends Application{
    private static WorkplanDataHolder instance;
    public static WorkplanDataHolder getInstance(){
        if(instance == null){
            instance = new WorkplanDataHolder();
        }
        return instance;
    }

    private List<Dictionary<Integer, List<Task>>> taskGroups = new LinkedList<>();

    public List<Dictionary<Integer, List<Task>>> getTaskGroups() {
        return taskGroups;
    }

    public void addTaskGroup(int groupNumber, List<Task> tasks){
        Dictionary<Integer, List<Task>> taskMap = new Hashtable<>();
        taskMap.put(groupNumber, tasks);
        taskGroups.add(taskMap);
    }
}
