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

    private Dictionary<Integer, List<Task>> taskGroups = new Hashtable<>();

    public Dictionary<Integer, List<Task>> getTaskGroups() {
        return taskGroups;
    }

    public void resetTaskGroups() {
        taskGroups = new Hashtable<>();
    }
}
