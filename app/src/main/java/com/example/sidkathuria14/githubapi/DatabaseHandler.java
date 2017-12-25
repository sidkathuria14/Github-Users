package com.example.sidkathuria14.githubapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sidkathuria14.githubapi.Models.User;

/**
 * Created by sidkathuria14 on 25/12/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;

    public static final String DB_TABLE = " Users ";
    public static final String DB_NAME = " Users ";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME= "name";
    public static final String KEY_PUBLIC_REPOS = "public_repos";
    public static final String KEY_PRIVATE_REPOS = "private_repos";
    public static final String KEY_FOLLOWERS = "followers";
    public static final String KEY_FOLLOWING = "following";
    public static final String KEY_PUBLIC_GISTS = "public_gists";
    public static final String KEY_BIO = "bio";
    public static final String KEY_TYPE = "type";

    public DatabaseHandler(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
String CREATE_CONTACTS_TABLE = "CREATE TABLE" + DB_TABLE +
         "( " + KEY_ID +  " INTEGER PRIMARY KEY, "
        + KEY_NAME + " TEXT, " + KEY_PUBLIC_REPOS + " INTEGER , " + KEY_PRIVATE_REPOS + " INTEGER , " +
        KEY_FOLLOWERS + " INTEGER, " + KEY_FOLLOWING + " INTEGER , " +
        KEY_PUBLIC_GISTS + " INTEGER, " + KEY_BIO + " TEXT, " + KEY_TYPE  + " TEXT "
        + " ) ";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }
    public void addUser(User user){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID,user.getId());
        values.put(KEY_NAME,user.getName());
        values.put(KEY_PUBLIC_REPOS,user.getPublic_repos());
        values.put(KEY_PRIVATE_REPOS,user.getPrivate_repos());
        values.put(KEY_FOLLOWERS,user.getFollowers());
        values.put(KEY_FOLLOWING,user.getFollowing());
        values.put(KEY_PUBLIC_GISTS,user.getPublic_gists());
        values.put(KEY_BIO,user.getBio());
        values.put(KEY_TYPE,user.getType());
        db.insert(DB_TABLE,null,values);
        db.close();

    }
    public int getCount(){
        String countQuery= "SELECT  * FROM " + DB_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DB_TABLE, new String[]{
                KEY_ID, KEY_NAME,KEY_PUBLIC_REPOS,KEY_PRIVATE_REPOS,KEY_FOLLOWERS,KEY_FOLLOWING,KEY_PUBLIC_GISTS,KEY_BIO,KEY_TYPE
        },KEY_ID + " =? ", new String[]{
                String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        User user = new User(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)),
                Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),cursor.getString(7),cursor.getString(8));
        return user;
    }

    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE,KEY_ID + " =? ",new String[]{
                String.valueOf(user.getId())
        });

        db.close();
    }

}
