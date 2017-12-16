package com.rynkbit.dienstplan.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rynkbit.dienstplan.db.DBHelper;
import com.rynkbit.dienstplan.entities.Shift;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 12/9/17.
 */

public class ShiftRepository implements Repository<Shift>{
    private static ShiftRepository instance;

    private final Context context;
    private final DBHelper dbHelper;

    public static ShiftRepository getInstance(Context context){
        if(instance == null){
            instance = new ShiftRepository(context);
        }
        return instance;
    }

    private ShiftRepository(Context context){
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public void merge(Shift object) {
        Shift toUpdate = getById(object.getId());
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(com.rynkbit.dienstplan.db.contract.Shift.Columns.NAME, object.getName());

        if(toUpdate == null){
            sqLiteDatabase.insert(
                    com.rynkbit.dienstplan.db.contract.Shift.TABLE,
                    null,
                    contentValues
            );
        }else{
            sqLiteDatabase.update(
                    com.rynkbit.dienstplan.db.contract.Shift.TABLE,
                    contentValues,
                    com.rynkbit.dienstplan.db.contract.Shift.Columns.ID + " = ?",
                    new String[]{String.valueOf(toUpdate.getId())}
            );
        }
    }

    @Override
    public void remove(long id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.delete(
                com.rynkbit.dienstplan.db.contract.Shift.TABLE,
                com.rynkbit.dienstplan.db.contract.Shift.Columns.ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }

    @Override
    public Shift getById(long id) {
        Shift result = null;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                com.rynkbit.dienstplan.db.contract.Shift.TABLE,
                null,
                com.rynkbit.dienstplan.db.contract.Shift.Columns.ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        if(cursor.moveToFirst()){
            result = shiftFromCursor(cursor);
        }

        return result;
    }

    @Override
    public List<Shift> getAll() {
        List<Shift> result = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                com.rynkbit.dienstplan.db.contract.Shift.TABLE,
                null, null, null, null, null, null
        );

        if(cursor.moveToFirst()){
            do{
                result.add(
                        shiftFromCursor(cursor)
                );
            }while (cursor.moveToNext());
        }

        return result;
    }

    private Shift shiftFromCursor(Cursor cursor){
        Shift shift = new Shift();
        long id = cursor.getLong(
                cursor.getColumnIndex(com.rynkbit.dienstplan.db.contract.Shift.Columns.ID)
        );
        String name = cursor.getString(
                cursor.getColumnIndex(com.rynkbit.dienstplan.db.contract.Shift.Columns.NAME)
        );

        shift.setId(id);
        shift.setName(name);

        return shift;
    }
}
