package com.rynkbit.dienstplan.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by michael on 11/25/17.
 */

public class Shift {
    private int id;
    private String name;

    public Shift() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-YYYY", Locale.ENGLISH);

        this.id = -1;
        this.name = simpleDateFormat.format(new Date());
    }

    public Shift(int id, String name) {
        this();

        this.id = id;
        this.name = name;
    }
}
