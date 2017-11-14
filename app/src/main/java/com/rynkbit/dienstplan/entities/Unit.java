package com.rynkbit.dienstplan.entities;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class Unit {
    private Date from;
    private Date to;

    private List<Soldier> soldiers;
    private List<Station> stations;

    public Unit() {
        soldiers = new LinkedList<>();
        stations = new LinkedList<>();
    }

    public Unit(Date from, Date to, List<Soldier> soldiers, List<Station> stations) {

        this.from = from;
        this.to = to;
        this.soldiers = soldiers;
        this.stations = stations;
    }
}
