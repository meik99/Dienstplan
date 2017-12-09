package com.rynkbit.dienstplan.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by michael on 11/25/17.
 */

public class Shift {
    private long id;
    private String name;

    public Shift() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-YYYY", Locale.ENGLISH);

        this.id = -1;
        this.name = simpleDateFormat.format(new Date());
    }

    public Shift(long id, String name) {
        this();

        this.id = id;
        this.name = name;
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
}
