package com.rynkbit.dienstplan.db.facade;

import android.content.Context;

import com.rynkbit.dienstplan.db.repository.SoldierRepository;
import com.rynkbit.dienstplan.entities.Soldier;

import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class SoldierFacade {
    private final Context context;

    public SoldierFacade(Context context) {
        this.context = context;
    }

    public void add(Soldier soldier){
        SoldierRepository.getInstance(context).add(soldier);
    }

    public void update(Soldier soldier){
        SoldierRepository.getInstance(context).merge(soldier);
    }

    public void remove(long id){
        SoldierRepository.getInstance(context).remove(id);
    }

    public List<Soldier> getAll() {
        return SoldierRepository.getInstance(context).getAll();
    }

    public Soldier getById(long soldier) {
        if(soldier < 0){
            return new Soldier();
        }else{
            Soldier result = SoldierRepository.getInstance(context).get(soldier);

            if(result == null){
                return new Soldier();
            }

            return result;
        }
    }
}
