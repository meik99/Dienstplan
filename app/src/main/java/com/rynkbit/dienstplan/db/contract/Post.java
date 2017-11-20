package com.rynkbit.dienstplan.db.contract;

/**
 * Created by michael on 11/16/17.
 */

public class Post {
    public static final String TABLE = "POST";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "(" +
                    Columns.ID + " INTEGER PRIMARY KEY," +
                    Columns.DESCRIPTION + " TEXT NOT NULL," +
                    Columns.EXERTION + " INTEGER" +
                    ");";
    public static final String DROP_TABLE =
            "DROP TABLE " + TABLE + ";";

    public class Columns{
        public static final String ID = "ID";
        public static final String DESCRIPTION = "DESCRIPTION";
        public static final String EXERTION = "EXERTION";
    }
}
