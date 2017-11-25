package com.rynkbit.dienstplan.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class Soldier{
    private Status status;
    private long id;
    private String name;
    private Positon positon;
    private boolean ill;
    private boolean weak;
    private int workingHours;
    private int exertion;

    private List<LikesPost> likesPostList;
    private List<SoldierConnection> soldierConnectionList;

    public Soldier(long id, String name, Positon positon, Status status, int exertion) {
        this();

        this.id = id;
        this.name = name;
        this.positon = positon;
        this.status = status;
        this.exertion = exertion;
    }

    public Soldier(long id, String name, int position, int status, int exertion) {
        this(
                id, name,
                Positon.values()[position],
                Status.values()[status],
                exertion
        );
    }

    public Soldier(){
        likesPostList = new LinkedList<>();
        soldierConnectionList = new LinkedList<>();
        this.id = -1;
        this.positon = Positon.Gunner;
        this.status = Status.Healthy;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Positon getPositon() {
        return positon;
    }

    public void setPositon(Positon positon) {
        this.positon = positon;
    }

    @Deprecated
    public boolean isIll() {
        return ill;
    }
    @Deprecated
    public void setIll(boolean ill) {
        this.ill = ill;
    }
    @Deprecated
    public boolean isWeak() {
        return weak;
    }
    @Deprecated
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

    public int getExertion() {
        return exertion;
    }

    public void setExertion(int exertion) {
        this.exertion = exertion;
    }

    public enum Positon {
        Driver, Commander, ViceCommander, Gunner
    }

    public enum Status {
        Ailling, Ill, Unavailable, Healthy
    }
}
