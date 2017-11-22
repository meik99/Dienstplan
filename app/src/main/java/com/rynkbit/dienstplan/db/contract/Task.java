package com.rynkbit.dienstplan.db.contract;

/**
 * Created by michael on 11/16/17.
 */

public class Task {
    public static final String TABLE = "TASK";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "(" +
                    Columns.ID + " INTEGER PRIMARY KEY," +
                    Columns.POST_ID + " INTEGER NOT NULL," +
                    Columns.SHIFT_ID + " INTEGER NOT NULL," +
                    Columns.TIME_FROM + " TEXT NOT NULL," +
                    Columns.TIME_TO+ " TEXT NOT NULL," +
                    Columns.GROUP + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + Columns.POST_ID + ") REFERENCES " + Post.TABLE + "(" + Post.Columns.ID + ")," +
                    "FOREIGN KEY (" + Columns.SHIFT_ID + ") REFERENCES " + Shift.TABLE + "(" + Shift.Columns.ID + ")" +
                    ");";
    public static final String DROP_TABLE =
            "DROP TABLE " + TABLE + ";";

    public class Columns {
        public static final String ID = "ID";
        public static final String SHIFT_ID = "SHIFT_ID";
        public static final String POST_ID = "POST_ID";
        public static final String TIME_FROM = "TIME_FROM";
        public static final String TIME_TO = "TIME_TO";
        public static final String GROUP = "GROUP";
    }
}
