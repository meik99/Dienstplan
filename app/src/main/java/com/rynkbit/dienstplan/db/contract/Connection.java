package com.rynkbit.dienstplan.db.contract;

/**
 * Created by michael on 11/16/17.
 */

public class Connection {
    public static final String TABLE = "CONNECTION";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "(" +
                    Columns.SOLDIER_FROM_ID + " INTEGER NOT NULL," +
                    Columns.SOLDIER_TO_ID + " INTEGER NOT NULL," +
                    Columns.STATE + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + Columns.SOLDIER_FROM_ID + ") REFERENCES " + Soldier.TABLE + "(" + Soldier.Columns.ID + ")," +
                    "FOREIGN KEY (" + Columns.SOLDIER_TO_ID + ") REFERENCES " + Soldier.TABLE + "(" + Soldier.Columns.ID + ")" +
                    ");";
    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + Connection.TABLE + ";";

    public class Columns{
        public static final String SOLDIER_FROM_ID = "SOLDIER_FROM_ID";
        public static final String SOLDIER_TO_ID = "SOLDIER_TO_ID";
        public static final String STATE = "STATE";
    }
}
