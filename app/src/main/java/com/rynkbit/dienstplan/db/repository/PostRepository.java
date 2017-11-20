package com.rynkbit.dienstplan.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rynkbit.dienstplan.db.DBHelper;
import com.rynkbit.dienstplan.entities.Post;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 11/12/17.
 */

public class PostRepository {
    private static PostRepository instance;

    public static PostRepository getInstance(Context context) {
        if(instance == null){
            instance = new PostRepository(context);
        }
        return instance;
    }

    private final DBHelper dbHelper;

    private PostRepository(Context context){
        dbHelper = new DBHelper(context);
    }

    public List<Post> getAll(){
        List<Post> result = new LinkedList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                com.rynkbit.dienstplan.db.contract.Post.TABLE,
                new String[]{
                        com.rynkbit.dienstplan.db.contract.Post.Columns.ID,
                        com.rynkbit.dienstplan.db.contract.Post.Columns.DESCRIPTION,
                        com.rynkbit.dienstplan.db.contract.Post.Columns.EXERTION
                },
                null, null, null, null, null
        );

        if(cursor.moveToFirst()){
            do{
                result.add(
                        postFromCursor(cursor)
                );
            }while (cursor.moveToNext());
        }

        return result;
    }

    public Post getById(long postId) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                com.rynkbit.dienstplan.db.contract.Post.TABLE,
                new String[]{
                        com.rynkbit.dienstplan.db.contract.Post.Columns.ID,
                        com.rynkbit.dienstplan.db.contract.Post.Columns.DESCRIPTION,
                        com.rynkbit.dienstplan.db.contract.Post.Columns.EXERTION
                },
                com.rynkbit.dienstplan.db.contract.Post.Columns.ID + " = ?",
                new String[]{ String.valueOf(postId) },
                null, null, null
        );

        if(cursor.moveToFirst()){
            return postFromCursor(cursor);
        }

        return null;
    }

    private Post postFromCursor(Cursor cursor){
        long id = cursor.getLong(
                cursor.getColumnIndex(
                        com.rynkbit.dienstplan.db.contract.Post.Columns.ID
                ));
        String description = cursor.getString(
                cursor.getColumnIndex(
                        com.rynkbit.dienstplan.db.contract.Post.Columns.DESCRIPTION
                ));
        int exertion = cursor.getInt(
                cursor.getColumnIndex(
                        com.rynkbit.dienstplan.db.contract.Post.Columns.EXERTION
                ));
        Post post = new Post(id, description, Post.PostBurden.values()[exertion]);

        return post;
    }

    public void merge(Post post) {
        Post toUpdate = getById(post.getId());
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();


        contentValues.put(
                com.rynkbit.dienstplan.db.contract.Post.Columns.DESCRIPTION,
                post.getName()
        );
        contentValues.put(
                com.rynkbit.dienstplan.db.contract.Post.Columns.EXERTION,
                post.getPostBurden().ordinal()
        );

        if(toUpdate != null){
            long id = post.getId();
            sqLiteDatabase.update(
                    com.rynkbit.dienstplan.db.contract.Post.TABLE,
                    contentValues,
                    com.rynkbit.dienstplan.db.contract.Post.Columns.ID + " = ?",
                    new String[]{String.valueOf(id)}
            );
        }else{
            sqLiteDatabase.insert(
                    com.rynkbit.dienstplan.db.contract.Post.TABLE,
                    null,
                    contentValues
            );
        }
    }
}
