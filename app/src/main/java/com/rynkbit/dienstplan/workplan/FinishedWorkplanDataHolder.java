package com.rynkbit.dienstplan.workplan;

import android.app.Application;

import com.rynkbit.dienstplan.entities.Shift;
import com.rynkbit.dienstplan.entities.Soldier;

import java.util.Dictionary;
import java.util.List;


/**
 * Created by michael on 12/16/17.
 */

public class FinishedWorkplanDataHolder extends Application {
    private static FinishedWorkplanDataHolder instance;
    private Dictionary<Integer, List<Soldier>> soldierPool;

    public static FinishedWorkplanDataHolder getInstance(){
        if(instance == null){
            instance = new FinishedWorkplanDataHolder();
        }

        return instance;
    }

    private FinishedWorkplanDataHolder() {}

    private Shift shift;
    private int groupNumber;

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void setSoldierPool(Dictionary<Integer, List<Soldier>> soldierPool) {
        this.soldierPool = soldierPool;
    }

    public Dictionary<Integer, List<Soldier>> getSoldierPool() {
        return soldierPool;
    }
}
