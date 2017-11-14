package com.rynkbit.dienstplan.db.facade;

import com.rynkbit.dienstplan.db.repository.SoldierRepository;
import com.rynkbit.dienstplan.entities.Soldier;

import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class SoldierFacade {
    public SoldierFacade() {
    }

    public void add(Soldier soldier){
        SoldierRepository.getInstance().add(soldier);
    }

    public void update(Soldier soldier){
        SoldierRepository.getInstance().merge(soldier);
    }

    public void remove(long id){
        SoldierRepository.getInstance().remove(id);
    }

    public List<Soldier> getAll() {
        return SoldierRepository.getInstance().getAll();
    }

    public Soldier getById(long soldier) {
        if(soldier < 0){
            return new Soldier();
        }else{
            Soldier result = SoldierRepository.getInstance().get(soldier);

            if(result == null){
                return new Soldier();
            }

            return result;
        }
    }
}
