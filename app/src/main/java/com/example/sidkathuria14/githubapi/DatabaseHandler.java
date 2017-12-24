package com.example.sidkathuria14.githubapi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sidkathuria14 on 25/12/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;

    public static final String DB_TABLE = "";
    public static final String DB_NAME = "";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME= "name";
    public static final String KEY_FOLLOWERS = "followers";
    public static final String KEY_FOLLOWING = "following";
    public static final String KEY_PUBLIC_GISTS = "public gists";
    public static final String KEY_BIO = "bio";
    public static final String KEY_TYPE = "type";

    public DatabaseHandler(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
String CREATE_CONTACTS_TABLE = "CREATE TABLE" + DB_TABLE +
         "( " + KEY_ID +  " INTEGER PRIMARY KEY,"
        + KEY_NAME + " TEXT," + KEY_FOLLOWERS + " TEXT" + KEY_FOLLOWING + " TEXT" +
        KEY_PUBLIC_GISTS + " TEXT" + KEY_BIO + " TEXT" + KEY_TYPE  + " TEXT"
        + " ) ";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
