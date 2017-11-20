package com.rynkbit.dienstplan.db.contract;

/**
 * Created by michael on 11/16/17.
 */

public class Shift {
    public static final String TABLE = "SHIFT";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "(" +
                    Columns.ID + " INTEGER PRIMARY KEY," +
                    Columns.DATE_FROM + " TEXT NOT NULL," +
                    Columns.DATE_TO + " TEXT NOT NULL" +
                    ");";

    public static final String DROP_TABLE =
            "DROP TABLE " + TABLE + ";";

    public class Columns {
        public static final String ID = "ID";
        public static final String DATE_FROM = "DATE_FROM";
        public static final String DATE_TO = "DATE_TO";
    }
}
