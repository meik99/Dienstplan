package com.rynkbit.dienstplan.db.contract;

/**
 * Created by michael on 11/16/17.
 */

public class Preferences {
    public static final String TABLE = "PREFERENCES";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "(" +
                    Columns.POST_ID + " INTEGER NOT NULL," +
                    Columns.SOLDIER_ID + " INTEGER NOT NULL," +
                    Columns.STATE + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + Columns.POST_ID + ") REFERENCES " + Post.TABLE + "(" + Post.Columns.ID + ")," +
                    "FOREIGN KEY (" + Columns.SOLDIER_ID + ") REFERENCES " + Soldier.TABLE + "(" + Soldier.Columns.ID + ")" +
                    ");";
    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE + ";";

    public class Columns{
        public static final String SOLDIER_ID = "SOLDIER_ID";
        public static final String POST_ID = "POST_ID";
        public static final String STATE = "STATE";
    }
}
