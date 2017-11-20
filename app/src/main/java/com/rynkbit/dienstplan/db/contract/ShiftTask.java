package com.rynkbit.dienstplan.db.contract;

/**
 * Created by michael on 11/19/17.
 */

public class ShiftTask {
    public static final String TABLE = "SHIFT_TASK";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "(" +
                    Columns.SHIFT_ID + " INTEGER NOT NULL," +
                    Columns.TASK_ID + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + Columns.SHIFT_ID + ") REFERENCES " + Shift.TABLE + "(" + Shift.Columns.ID + ")," +
                    "FOREIGN KEY (" + Columns.TASK_ID + ") REFERENCES " + Task.TABLE + "(" + Task.Columns.ID + ")" +
                    ");";

    public static final String DROP_TABLE =
            "DROP TABLE " + TABLE + ";";

    public class Columns {
        public static final String SHIFT_ID = "SHIFT_ID";
        public static final String TASK_ID = "TASK_ID";

    }
}
