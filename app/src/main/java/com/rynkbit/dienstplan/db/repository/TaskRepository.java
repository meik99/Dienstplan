package com.rynkbit.dienstplan.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.rynkbit.dienstplan.db.DBHelper;
import com.rynkbit.dienstplan.entities.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 12/1/17.
 */

public class TaskRepository implements Repository<Task>{
    private static TaskRepository instance;
    private final DBHelper dbHelper;

    public static TaskRepository getInstance(Context context){
        if(instance == null){
            instance = new TaskRepository(context);
        }
        return instance;
    }

    private TaskRepository(Context context){
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public void merge(Task object) {
        Task toUpdate = null;
        ContentValues contentValues = new ContentValues();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        if(object.getId() != -1){
            toUpdate = getById(object.getId());
        }

        contentValues.put(
                com.rynkbit.dienstplan.db.contract.Task.Columns.GROUP,
                object.getGroupNumber()
        );
        contentValues.put(
                com.rynkbit.dienstplan.db.contract.Task.Columns.POST_ID,
                object.getPostId()
        );
        contentValues.put(
                com.rynkbit.dienstplan.db.contract.Task.Columns.SHIFT_ID,
                object.getShiftId()
        );
        contentValues.put(
                com.rynkbit.dienstplan.db.contract.Task.Columns.TIME_FROM,
                simpleDateFormat.format(object.getTimeFrom())
        );
        contentValues.put(
                com.rynkbit.dienstplan.db.contract.Task.Columns.TIME_TO,
                simpleDateFormat.format(object.getTimeTo())
        );


        if(toUpdate != null){
            database.update(
                    com.rynkbit.dienstplan.db.contract.Task.TABLE,
                    contentValues,
                    com.rynkbit.dienstplan.db.contract.Task.Columns.ID + " = ?",
                    new String[]{String.valueOf(toUpdate.getId())}
            );
        }else{
            database.insert(
                    com.rynkbit.dienstplan.db.contract.Task.TABLE,
                    null,
                    contentValues
            );
        }
    }

    @Override
    public void remove(long id) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(
                com.rynkbit.dienstplan.db.contract.Task.TABLE,
                com.rynkbit.dienstplan.db.contract.Task.Columns.ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }

    @Override
    public Task getById(long id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(
                com.rynkbit.dienstplan.db.contract.Task.TABLE,
                null,
                com.rynkbit.dienstplan.db.contract.Task.Columns.ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        if(cursor.moveToFirst()){
            return taskFromCursor(cursor);
        }

        return null;
    }

    @Override
    public List<Task> getAll() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(
                com.rynkbit.dienstplan.db.contract.Task.TABLE,
                null,null, null, null, null, null
        );
        List<Task> result = new LinkedList<>();

        if(cursor.moveToFirst()){
            do{
                result.add(
                        taskFromCursor(cursor)
                );
            }while (cursor.moveToNext());
        }

        return result;
    }

    private Task taskFromCursor(Cursor c){
        Task task = new Task();
        long id = c.getLong(
          c.getColumnIndex(
                  com.rynkbit.dienstplan.db.contract.Task.Columns.ID
          ));
        long postId = c.getLong(
                c.getColumnIndex(
                        com.rynkbit.dienstplan.db.contract.Task.Columns.POST_ID
                ));
        long shiftId = c.getLong(
                c.getColumnIndex(
                        com.rynkbit.dienstplan.db.contract.Task.Columns.SHIFT_ID
                ));
        int groupNumber = c.getInt(
                c.getColumnIndex(
                        com.rynkbit.dienstplan.db.contract.Task.Columns.GROUP
                ));
        String timeFrom = c.getString(
                c.getColumnIndex(
                        com.rynkbit.dienstplan.db.contract.Task.Columns.TIME_FROM
                ));
        String timeTo = c.getString(
                c.getColumnIndex(
                        com.rynkbit.dienstplan.db.contract.Task.Columns.TIME_TO
                ));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date dateTimeFrom;
        Date dateTimeTo;

        try {
            dateTimeFrom = simpleDateFormat.parse(timeFrom);
        } catch (ParseException e) {
            dateTimeFrom = null;
        }
        try {
            dateTimeTo = simpleDateFormat.parse(timeTo);
        } catch (ParseException e) {
            dateTimeTo = null;
        }

        task.setId(id);
        task.setPostId(postId);
        task.setShiftId(shiftId);
        task.setGroupNumber(groupNumber);
        task.setTimeFrom(dateTimeFrom);
        task.setTimeTo(dateTimeTo);

        return task;
    }
}
