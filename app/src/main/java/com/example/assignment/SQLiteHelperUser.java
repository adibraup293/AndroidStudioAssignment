package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelperUser extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS ="users";

    //TABLE USERS COLUMNS
    public static final String KEY_USER_NAME = "username";  //COLUMN user name @primaryKey
    public static final String KEY_PASSWORD = "password";//COLUMN password
    public static final String KEY_NAME = "name";//COLUMN name
    public static final String KEY_EMAIL = "email";//COLUMN email
    public static final String KEY_MONTHLY_INCOME = "monthlyIncome";//COLUMN monthly income
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS //SQL for creating users table
            + " ( "
            + KEY_USER_NAME + " TEXT PRIMARY KEY, "
            + KEY_PASSWORD + " TEXT,"
            + KEY_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_MONTHLY_INCOME + " DOUBLE"
            + " ) ";


    public SQLiteHelperUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    //Adding users into the users table
    public void addUser (User user){
        SQLiteDatabase db = this.getWritableDatabase(); //get writable database
        ContentValues values = new ContentValues(); //create content values to insert
        values.put(KEY_USER_NAME, user.getUsername()); //Put username in  @values
        values.put(KEY_PASSWORD, user.getPassword()); //Put password in  @values
        values.put(KEY_NAME, user.getName()); //Put name in  @values
        values.put(KEY_EMAIL, user.getEmail()); //Put email in  @values
        values.put(KEY_MONTHLY_INCOME, user.getMonthlyIncome()); //Put monthly income in  @values
        long todo_id = db.insert(TABLE_USERS, null, values); // insert row
    }

    //checking if there are users in the database
    public User Authenticate(User user){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USERS, //selecting the users table
                new String[] {KEY_USER_NAME, KEY_PASSWORD},
                KEY_USER_NAME + "=?",
                new String[]{user.getUsername()},//Where clause
                null,null,null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() >0 ){
            //if cursor has value then in user database there is user associated with this given username
            User user1 = new User(cursor.getString(0),cursor.getString(1));

            //Match both passwords check they are same or not
            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }
        //if user password does not matches or there is no record with that username then return
        return null;

    }

}
