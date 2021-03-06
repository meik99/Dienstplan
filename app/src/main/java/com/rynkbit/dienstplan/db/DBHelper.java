package com.rynkbit.dienstplan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rynkbit.dienstplan.db.contract.Connection;
import com.rynkbit.dienstplan.db.contract.Post;
import com.rynkbit.dienstplan.db.contract.Preferences;
import com.rynkbit.dienstplan.db.contract.Shift;
import com.rynkbit.dienstplan.db.contract.Soldier;
import com.rynkbit.dienstplan.db.contract.SoldierGroup;
import com.rynkbit.dienstplan.db.contract.Task;


/**
 * Created by michael on 11/12/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Workplan.sqlite";
    public static final int DB_VERSION = 15;

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
        sqLiteDatabase.execSQL(SoldierGroup.CREATE_TABLE);

        sqLiteDatabase.execSQL(
                "INSERT INTO "  + Soldier.TABLE + " VALUES (NULL, 'Los', " +
                        com.rynkbit.dienstplan.entities.Soldier.Positon.Driver.ordinal() +
                        ", 0, 0);"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO "  + Soldier.TABLE + " VALUES (NULL, 'Maier', " +
                        com.rynkbit.dienstplan.entities.Soldier.Positon.Commander.ordinal() +
                        ", 0, 0);"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO "  + Soldier.TABLE + " VALUES (NULL, 'Pissenberger', 0, 0, 0);"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO "  + Soldier.TABLE + " VALUES (NULL, 'Pöchtrager', 0, 0, 0);"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO "  + Soldier.TABLE + " VALUES (NULL, 'Rynkiewicz', 0, 0, 0);"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO "  + Soldier.TABLE + " VALUES (NULL, 'Vicovan', 0, 0, 0);"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO "  + Soldier.TABLE + " VALUES (NULL, 'Zeppezauer', " +
                        com.rynkbit.dienstplan.entities.Soldier.Positon.ViceCommander.ordinal() +
                        ", 0, 0);"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO " + Post.TABLE + " VALUES (NULL, 'COP 2', " +
                        com.rynkbit.dienstplan.entities.Post.PostBurden.Light.ordinal() +
                        ");"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO " + Post.TABLE + " VALUES (NULL, 'COP 3', " +
                        com.rynkbit.dienstplan.entities.Post.PostBurden.Light.ordinal() +
                        ");"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO " + Post.TABLE + " VALUES (NULL, 'COP 4', " +
                        com.rynkbit.dienstplan.entities.Post.PostBurden.Light.ordinal() +
                        ");"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO " + Post.TABLE + " VALUES (NULL, 'COP 5', " +
                        com.rynkbit.dienstplan.entities.Post.PostBurden.Medium.ordinal() +
                        ");"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO " + Post.TABLE + " VALUES (NULL, 'COP 6', " +
                        com.rynkbit.dienstplan.entities.Post.PostBurden.Medium.ordinal() +
                        ");"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO " + Post.TABLE + " VALUES (NULL, 'COP 11', " +
                        com.rynkbit.dienstplan.entities.Post.PostBurden.Hard.ordinal() +
                        ");"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO " + Post.TABLE + " VALUES (NULL, 'Funny Hill', " +
                        com.rynkbit.dienstplan.entities.Post.PostBurden.Hard.ordinal() +
                        ");"
        );
        sqLiteDatabase.execSQL(
                "INSERT INTO " + Post.TABLE + " VALUES (NULL, 'SPIELFELD', " +
                        com.rynkbit.dienstplan.entities.Post.PostBurden.None.ordinal() +
                        ");"
        );
//        sqLiteDatabase.execSQL(
//                "INSERT INTO " + Shift.TABLE + " VALUES (NULL, '10-11-2017');"
//        );
//        sqLiteDatabase.execSQL(
//                "INSERT INTO " + Shift.TABLE + " VALUES (NULL, '11-11-2017');"
//        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SoldierGroup.DROP_TABLE);
        sqLiteDatabase.execSQL(Shift.DROP_TABLE);
        sqLiteDatabase.execSQL(Task.DROP_TABLE);
        sqLiteDatabase.execSQL(Connection.DROP_TABLE);
        sqLiteDatabase.execSQL(Preferences.DROP_TABLE);
        sqLiteDatabase.execSQL(Soldier.DROP_TABLE);
        sqLiteDatabase.execSQL(Post.DROP_TABLE);

        onCreate(sqLiteDatabase);
    }
}
