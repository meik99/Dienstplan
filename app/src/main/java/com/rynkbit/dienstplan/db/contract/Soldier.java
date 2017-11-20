package com.rynkbit.dienstplan.db.contract;

/**
 * Created by michael on 11/16/17.
 */

public class Soldier {
    public static final String TABLE = "SOLDIER";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "(" +
                    Columns.ID + " INTEGER PRIMARY KEY," +
                    Columns.NAME + " TEXT NOT NULL," +
                    Columns.POSITION + " INTEGER NOT NULL," +
                    Columns.EXERTION + " INTEGER," +
                    Columns.STATUS + " INTEGER" +
                    ");";

    public static final String DROP_TABLE =
            "DROP TABLE " + TABLE + ";";

    public class Columns{
        public static final String ID = "ID";
        public static final String NAME = "NAME";
        public static final String POSITION = "POSITION";
        public static final String STATUS = "STATUS";
        public static final String EXERTION = "EXERTION";
    }
}
