package com.rynkbit.dienstplan.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class Soldier{
    private long id;
    private String name;
    private SoldierType soldierType;
    private boolean ill;
    private boolean weak;
    private int workingHours;

    private List<LikesPost> likesPostList;
    private List<SoldierConnection> soldierConnectionList;

    public Soldier(long id, String name, SoldierType soldierType, boolean ill, boolean weak, int workingHours) {
        this();

        this.id = id;
        this.name = name;
        this.soldierType = soldierType;
        this.ill = ill;
        this.weak = weak;
        this.workingHours = workingHours;
    }

    public Soldier() {
        likesPostList = new LinkedList<>();
        soldierConnectionList = new LinkedList<>();
        id = -1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SoldierType getSoldierType() {
        return soldierType;
    }

    public void setSoldierType(SoldierType soldierType) {
        this.soldierType = soldierType;
    }

    public boolean isIll() {
        return ill;
    }

    public void setIll(boolean ill) {
        this.ill = ill;
    }

    public boolean isWeak() {
        return weak;
    }

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public List<LikesPost> getLikesPostList() {
        return likesPostList;
    }

    public void setLikesPostList(List<LikesPost> likesPostList) {
        this.likesPostList = likesPostList;
    }

    public List<SoldierConnection> getSoldierConnectionList() {
        return soldierConnectionList;
    }

    public void setSoldierConnectionList(List<SoldierConnection> soldierConnectionList) {
        this.soldierConnectionList = soldierConnectionList;
    }

    public enum SoldierType{
        Driver, Commander, ViceCommander, Normal
    }
}
