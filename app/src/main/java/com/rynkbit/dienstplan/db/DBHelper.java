package com.rynkbit.dienstplan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rynkbit.dienstplan.db.contract.Connection;
import com.rynkbit.dienstplan.db.contract.Post;
import com.rynkbit.dienstplan.db.contract.Preferences;
import com.rynkbit.dienstplan.db.contract.Shift;
import com.rynkbit.dienstplan.db.contract.ShiftTask;
import com.rynkbit.dienstplan.db.contract.Soldier;
import com.rynkbit.dienstplan.db.contract.Task;


/**
 * Created by michael on 11/12/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Workplan.sqlite";
    public static final int DB_VERSION = 4;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Post.CREATE_TABLE);
        sqLiteDatabase.execSQL(Soldier.CREATE_TABLE);
        sqLiteDatabase.execSQL(Preferences.CREATE_TABLE);
        sqLiteDatabase.execSQL(Connection.CREATE_TABLE);
        sqLiteDatabase.execSQL(Task.CREATE_TABLE);
        sqLiteDatabase.execSQL(Shift.CREATE_TABLE);
        sqLiteDatabase.execSQL(ShiftTask.CREATE_TABLE);

        sqLiteDatabase.execSQL(
                "INSERT INTO "  + Soldier.TABLE + " VALUES (NULL, 'SOLDIER 1', 0, 0, 0);"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO "  + Soldier.TABLE + " VALUES (NULL, 'SOLDIER 2', 0, 0, 0);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ShiftTask.DROP_TABLE);
        sqLiteDatabase.execSQL(Shift.DROP_TABLE);
        sqLiteDatabase.execSQL(Task.DROP_TABLE);
        sqLiteDatabase.execSQL(Connection.DROP_TABLE);
        sqLiteDatabase.execSQL(Preferences.DROP_TABLE);
        sqLiteDatabase.execSQL(Soldier.DROP_TABLE);
        sqLiteDatabase.execSQL(Post.DROP_TABLE);
    }
}
