package com.example.testclientjodit2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "JoditLite";

    public static final String TABLE_USERS = "jodit_user";
    public static final String TABLE_GROUPS = "jodit_group";

    public static final String KEY_ID = "_id";

    public static final String KEY_USER_JSON = "key_user";
    public static final String KEY_GROUP_JSON = "key_group";

    public final static String FILE_NAME_SESSION = "session.txt";
    public final static String FILE_NAME_USER = "user.txt";
    public final static String FILE_NAME_USER_SESSION = "user_session.txt";

    public final static String INTENT_KEY_SESSION = "intent_session";
    public final static String INTENT_KEY_USER_SESSION = "intent_user_session";
    public final static String INTENT_KEY_USER = "intent_user";

    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS +
                "(" + KEY_ID + " integer primary key," + KEY_USER_JSON + " text " + ")");

        db.execSQL("create table " + TABLE_GROUPS +
                "(" + KEY_ID + " integer primary key," + KEY_GROUP_JSON + " text " + ")");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USERS);
        db.execSQL("drop table if exists " + TABLE_GROUPS);
        onCreate(db);
    }
}
