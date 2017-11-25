package com.rynkbit.dienstplan.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rynkbit.dienstplan.db.DBHelper;
import com.rynkbit.dienstplan.entities.Soldier;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class SoldierRepository {
    private static SoldierRepository instance;

    public static SoldierRepository getInstance(Context context) {
        if(instance == null){
            instance = new SoldierRepository(context);
        }

        return instance;
    }

    private List<Soldier> soldiers;
    private DBHelper dbHelper;

    private SoldierRepository(Context context) {
        dbHelper = new DBHelper(context);
    }


    public void remove(long id){
        for(int i = 0; i < soldiers.size(); i++){
            if(soldiers.get(i).getId() == id){
                soldiers.remove(i);
                i = soldiers.size();
            }
        }
    }

    public void merge(Soldier soldier){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = sqLiteDatabase.query(
                com.rynkbit.dienstplan.db.contract.Soldier.TABLE,
                new String[]{
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.ID,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.NAME,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.EXERTION,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.POSITION,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.STATUS
                },
                com.rynkbit.dienstplan.db.contract.Soldier.Columns.ID + " = ?",
                new String[]{
                        String.valueOf(soldier.getId())
                },
                null, null, null
        );

        contentValues.put(
                com.rynkbit.dienstplan.db.contract.Soldier.Columns.NAME, soldier.getName()
        );
        contentValues.put(
                com.rynkbit.dienstplan.db.contract.Soldier.Columns.EXERTION, soldier.getExertion()
        );
        contentValues.put(
                com.rynkbit.dienstplan.db.contract.Soldier.Columns.POSITION, soldier.getPositon().ordinal()
        );
        contentValues.put(
                com.rynkbit.dienstplan.db.contract.Soldier.Columns.STATUS, soldier.getStatus().ordinal()
        );


        int affectedRows = sqLiteDatabase.update(
                com.rynkbit.dienstplan.db.contract.Soldier.TABLE,
                contentValues,
                com.rynkbit.dienstplan.db.contract.Soldier.Columns.ID + " = ?",
                new String[]{ String.valueOf(soldier.getId())}
        );

        //Try updating row. If no is row affected insert a new one
        if(affectedRows <= 0){
            sqLiteDatabase.insert(
                    com.rynkbit.dienstplan.db.contract.Soldier.TABLE,
                    null,
                    contentValues
            );
        }
    }

    public List<Soldier> getAll() {
        List<Soldier> result = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                com.rynkbit.dienstplan.db.contract.Soldier.TABLE,
                new String[]{
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.ID,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.NAME,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.EXERTION,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.POSITION,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.STATUS
                },
                null,null,null,null,null
        );

        if(cursor.moveToFirst()){
            do{
                long id = cursor.getLong(
                        cursor.getColumnIndex(
                                com.rynkbit.dienstplan.db.contract.Soldier.Columns.ID
                        ));
                String name = cursor.getString(
                        cursor.getColumnIndex(
                                com.rynkbit.dienstplan.db.contract.Soldier.Columns.NAME
                        ));
                int exertion = cursor.getInt(
                        cursor.getColumnIndex(
                                com.rynkbit.dienstplan.db.contract.Soldier.Columns.EXERTION
                        ));
                int position = cursor.getInt(
                        cursor.getColumnIndex(
                                com.rynkbit.dienstplan.db.contract.Soldier.Columns.POSITION
                        ));
                int status = cursor.getInt(
                        cursor.getColumnIndex(
                                com.rynkbit.dienstplan.db.contract.Soldier.Columns.STATUS
                        ));

                Soldier soldier = new Soldier(
                        id, name, position, status, exertion
                );
                result.add(soldier);
            }while(cursor.moveToNext());
        }

        return result;
    }

    public Soldier get(long soldierId) {
        Soldier result = new Soldier();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                com.rynkbit.dienstplan.db.contract.Soldier.TABLE,
                new String[]{
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.ID,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.NAME,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.STATUS,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.POSITION,
                        com.rynkbit.dienstplan.db.contract.Soldier.Columns.EXERTION
                },
                com.rynkbit.dienstplan.db.contract.Soldier.Columns.ID + " = ?",
                new String[]{
                        String.valueOf(soldierId)
                },
                null, null, null
        );

        if(cursor.moveToFirst()){
            String name = cursor.getString(
                    cursor.getColumnIndex(
                            com.rynkbit.dienstplan.db.contract.Soldier.Columns.NAME
                    ));
            int status = cursor.getInt(
                    cursor.getColumnIndex(
                            com.rynkbit.dienstplan.db.contract.Soldier.Columns.STATUS
                    ));
            int exertion = cursor.getInt(
                    cursor.getColumnIndex(
                            com.rynkbit.dienstplan.db.contract.Soldier.Columns.EXERTION
                    ));
            int position = cursor.getInt(
                    cursor.getColumnIndex(
                            com.rynkbit.dienstplan.db.contract.Soldier.Columns.POSITION
                    ));

            result = new Soldier(
                    soldierId, name, position, status, exertion
            );
        }

        return result;
    }
}
