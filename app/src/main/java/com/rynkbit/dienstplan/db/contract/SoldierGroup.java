package com.rynkbit.dienstplan.db.contract;

/**
 * Created by michael on 11/22/17.
 */

public class SoldierGroup {
    public static final String TABLE = "SOLDIER_GROUP";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "(" +
                    Columns.SOLDIER_ID + " INTEGER NOT NULL," +
                    Columns.GROUP + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + Columns.SOLDIER_ID + ") REFERENCES " + Soldier.TABLE + " (" + Soldier.Columns.ID + ")" +
                    ");";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE + ";";

    public class Columns {
        public static final String SOLDIER_ID = "SOLDIER_ID";
        public static final String GROUP = "GROUP_NUMBER";
    }
}
