package com.example.testclientjodit2.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MySQL {


    public static void add(String json, String table, String key, DBHelper dbHelper){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(key, json);
        database.insert(table, null, contentValues);
        dbHelper.close();
    }

    public static ArrayList<String> get_all_value(String table, String key, DBHelper dbHelper){
        ArrayList<String> value = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(table, null, null,
                null, null, null, null);

        if(cursor.moveToFirst()){
            int index = cursor.getColumnIndex(key);
            do{
                value.add(cursor.getString(index));
            }while (cursor.moveToNext());
            cursor.close();
        }
        dbHelper.close();
        return value;
    }

    public static void delete_all(String table, DBHelper dbHelper){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(table, null, null);
        dbHelper.close();
    }

    public static void update(String json, String id, String table, String key, DBHelper dbHelper){
        if(!id.equalsIgnoreCase("")){
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(key, json);
            database.update(table, contentValues, DBHelper.KEY_ID + "= ?", new String[]{id});
            dbHelper.close();
        }
    }

    public static void delete(String table, String id, DBHelper dbHelper){
        if(!id.equalsIgnoreCase("")){
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            database.delete(table, DBHelper.KEY_ID + "=" + id, null);
            dbHelper.close();
        }
    }
}
