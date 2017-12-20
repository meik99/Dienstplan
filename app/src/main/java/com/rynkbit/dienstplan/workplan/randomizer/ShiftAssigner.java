package com.rynkbit.dienstplan.workplan.randomizer;

import android.content.Context;

import com.rynkbit.dienstplan.db.facade.PostFacade;
import com.rynkbit.dienstplan.db.facade.ShiftFacade;
import com.rynkbit.dienstplan.db.facade.SoldierFacade;
import com.rynkbit.dienstplan.entities.Shift;
import com.rynkbit.dienstplan.entities.Soldier;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 12/20/17.
 */

public class ShiftAssigner {
    private List<Soldier> pool;
    private List<Soldier> driver;
    private List<Soldier> commander;
    private Shift shift;
    private SoldierFacade soldierFacade;
    private ShiftFacade shiftFacade;
    private PostFacade postFacade;

    public ShiftAssigner(Context context, Shift shift){
        this.shift = shift;
        soldierFacade = new SoldierFacade(context);
        driver = new LinkedList<>();
        commander = new LinkedList<>();

        pool = soldierFacade.getAll();

        for(int i = 0; i < pool.size(); i++){
            Soldier soldier = pool.get(i);
            if(soldier.getPositon() == Soldier.Positon.Driver){
                driver.add(soldier);
                pool.remove(i--);
            }
            else if(
                    soldier.getPositon() == Soldier.Positon.Commander ||
                            soldier.getPositon() == Soldier.Positon.ViceCommander
                    ){
                commander.add(soldier);
                pool.remove(i--);
            }
        }
    }

    public void assign(){

    }
}
