package com.example.assignment.Admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment.Admin.UserAdmin;

public class SQLiteHelperUserAdmin extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserAdminDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERSADMIN ="userAdmin";

    //TABLE USERS COLUMNS
    public static final String KEY_STAFF_ID = "staffID"; // COLUMN Staff ID @primaryKey
    public static final String KEY_USER_NAME = "username";  //COLUMN username
    public static final String KEY_PASSWORD = "password";//COLUMN password
    public static final String KEY_NAME = "name";//COLUMN name
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERSADMIN //SQL for creating userAdmin table
            + " ( "
            + KEY_STAFF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
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
        sqLiteDatabase.execSQL("INSERT INTO userAdmin (username, password, name) VALUES('Admin', 'Admin', 'Daniel Toh')");
        sqLiteDatabase.execSQL("INSERT INTO userAdmin (username, password, name) VALUES('Admin2', 'Admin2', 'Nicholas Kelsey')");
        sqLiteDatabase.execSQL("INSERT INTO userAdmin (username, password, name) VALUES('Admin3\', 'Admin3', 'Yue Qi Dong')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERSADMIN);
    }

    // Adding new User Details
    public void createUserAdminDetails(String username, String password, String name){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_USER_NAME, username);
        cValues.put(KEY_PASSWORD, password);
        cValues.put(KEY_NAME, name);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_USERSADMIN,null, cValues);
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
