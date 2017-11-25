package com.rynkbit.dienstplan.db.contract;

/**
 * Created by michael on 11/16/17.
 */

public class Shift {
    public static final String TABLE = "SHIFT";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "(" +
                    Columns.ID + " INTEGER PRIMARY KEY," +
                    Columns.NAME + " TEXT NOT NULL" +
                    ");";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE + ";";

    public class Columns {
        public static final String ID = "ID";
        public static final String NAME = "NAME";
    }
}
