package com.rynkbit.dienstplan.db.repository;

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
//
//        soldiers = new LinkedList<>();
//
//        this.add(new Soldier(
//                0,
//                "Soldier 1",
//                Soldier.Positon.Normal,
//                true,
//                false,
//                20
//        ));
//        this.add(new Soldier(
//                1,
//                "Soldier 2",
//                Soldier.Positon.Normal,
//                false,
//                false,
//                40
//        ));
    }

    public void add(Soldier soldier){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
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
        if(soldier.getId() > -1){
            for(int i = 0; i < soldiers.size(); i++){
                if(soldiers.get(i).getId() == soldier.getId()){
                    Soldier s = soldiers.get(i);
                    s.setName(soldier.getName());
                    s.setIll(soldier.isIll());
                    s.setWeak(soldier.isWeak());
                    s.setPositon(soldier.getPositon());
                    s.setWorkingHours(soldier.getWorkingHours());
                    s.setLikesPostList(soldier.getLikesPostList());
                    s.setSoldierConnectionList(soldier.getSoldierConnectionList());

                    soldiers.set(i, soldier);
                }
            }
        }else{
            add(soldier);
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
        for(int i = 0; i < soldiers.size(); i++){
            if(soldiers.get(i).getId() == soldierId){
                return soldiers.get(i);
            }
        }

        return new Soldier();
    }
}
