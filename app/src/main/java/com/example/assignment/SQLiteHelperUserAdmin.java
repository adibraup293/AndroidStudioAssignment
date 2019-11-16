package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelperUserAdmin extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserAdminDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERSADMIN ="userAdmin";

    //TABLE USERS COLUMNS
    public static final String KEY_STAFF_ID = "staffID"; // COLUMN Staff ID @primaryKey
    public static final String KEY_USER_NAME = "username";  //COLUMN user name @primaryKey
    public static final String KEY_PASSWORD = "password";//COLUMN password
    public static final String KEY_NAME = "name";//COLUMN name
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERSADMIN //SQL for creating userAdmin table
            + " ( "
            + KEY_STAFF_ID + " INT PRIMARY KEY AUTOINCREMENT, "
            + KEY_USER_NAME + " TEXT,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_NAME + " TEXT"
            + " ) ";


    public SQLiteHelperUserAdmin(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues insertValues = new ContentValues();

        //first Admin
        insertValues.put(KEY_USER_NAME, "Admin"); //Put username in  @values
        insertValues.put(KEY_PASSWORD, "Admin"); //Put password in  @values
        insertValues.put(KEY_NAME, "Daniel Toh"); //Put name in  @values

        //second Admin
        insertValues.put(KEY_USER_NAME, "Admin2"); //Put username in  @values
        insertValues.put(KEY_PASSWORD, "Admin2"); //Put password in  @values
        insertValues.put(KEY_NAME, "Nicholas Kelsey"); //Put name in  @values

        //third Admin
        insertValues.put(KEY_USER_NAME, "Admin3"); //Put username in  @values
        insertValues.put(KEY_PASSWORD, "Admin4"); //Put password in  @values
        insertValues.put(KEY_NAME, "Yue Qi Dong"); //Put name in  @values
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERSADMIN);
    }

    //checking if there are user admin in the database
    public UserAdmin AuthenticateAdmin(UserAdmin userAdmin){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USERSADMIN, //selecting the users table
                new String[] {KEY_USER_NAME, KEY_PASSWORD},
                KEY_USER_NAME + "=?",
                new String[]{userAdmin.getUsername()},//Where clause
                null,null,null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() >0 ){
            //if cursor has value then in user database there is user associated with this given username
            UserAdmin userAdmin1 = new UserAdmin(cursor.getString(0),cursor.getString(1));

            //Match both passwords check they are same or not
            if (userAdmin.getPassword().equalsIgnoreCase(userAdmin1.getPassword())) {
                return userAdmin1;
            }
        }
        //if user password does not matches or there is no record with that username then return
        return null;

    }

}
