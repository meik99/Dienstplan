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
                    Columns.SOLDIER_ID + " INTEGER NOT NULL," +
                    Columns.TIME_FROM + " TEXT NOT NULL," +
                    Columns.TIME_TO+ " TEXT NOT NULL," +
                    "FOREIGN KEY (" + Columns.POST_ID + ") REFERENCES " + Post.TABLE + "(" + Post.Columns.ID + ")," +
                    "FOREIGN KEY (" + Columns.SOLDIER_ID + ") REFERENCES " + Soldier.TABLE + "(" + Soldier.Columns.ID + ")" +
                    ");";
    public static final String DROP_TABLE =
            "DROP TABLE " + TABLE + ";";

    public class Columns {
        public static final String ID = "ID";
        public static final String SOLDIER_ID = "SOLDIER_ID";
        public static final String POST_ID = "POST_ID";
        public static final String TIME_FROM = "TIME_FROM";
        public static final String TIME_TO = "TIME_TO";
    }
}
