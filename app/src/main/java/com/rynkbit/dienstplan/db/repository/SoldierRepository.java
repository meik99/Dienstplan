package com.rynkbit.dienstplan.db.repository;

import com.rynkbit.dienstplan.entities.Soldier;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class SoldierRepository {
    private static SoldierRepository instance;

    public static SoldierRepository getInstance() {
        if(instance == null){
            instance = new SoldierRepository();
        }

        return instance;
    }

    private List<Soldier> soldiers;

    private SoldierRepository() {
        soldiers = new LinkedList<>();

        this.add(new Soldier(
                0,
                "Soldier 1",
                Soldier.SoldierType.Normal,
                true,
                false,
                20
        ));
        this.add(new Soldier(
                1,
                "Soldier 2",
                Soldier.SoldierType.Normal,
                false,
                false,
                40
        ));
    }

    public void add(Soldier soldier){
        soldier.setId(soldiers.size());
        soldiers.add(soldier);
    }

    public void remove(long id){
        for(int i = 0; i < soldiers.size(); i++){
            if(soldiers.get(i).getId() == id){
                soldiers.remove(i);
                i = soldiers.size();
            }
        }
    }

    public void merge(Soldier soldier){
        if(soldier.getId() > -1){
            for(int i = 0; i < soldiers.size(); i++){
                if(soldiers.get(i).getId() == soldier.getId()){
                    Soldier s = soldiers.get(i);
                    s.setName(soldier.getName());
                    s.setIll(soldier.isIll());
                    s.setWeak(soldier.isWeak());
                    s.setSoldierType(soldier.getSoldierType());
                    s.setWorkingHours(soldier.getWorkingHours());
                    s.setLikesPostList(soldier.getLikesPostList());
                    s.setSoldierConnectionList(soldier.getSoldierConnectionList());

                    soldiers.set(i, soldier);
                }
            }
        }else{
            add(soldier);
        }
    }

    public List<Soldier> getAll() {
        return soldiers;
    }

    public Soldier get(long soldier) {
        for(int i = 0; i < soldiers.size(); i++){
            if(soldiers.get(i).getId() == soldier){
                return soldiers.get(i);
            }
        }

        return null;
    }
}
