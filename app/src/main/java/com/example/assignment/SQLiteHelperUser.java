package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

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


    // Adding new User Details
    void insertUserDetails(String username, String password, String name, String email, Double monthlyIncome){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_USER_NAME, username);
        cValues.put(KEY_PASSWORD, password);
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_EMAIL, email);
        cValues.put(KEY_MONTHLY_INCOME, monthlyIncome);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_USERS,null, cValues);
    }

    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT username, password FROM "+ TABLE_USERS;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("username",cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
            user.put("password",cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("email",cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            user.put("monthly income",cursor.getString(cursor.getColumnIndex(KEY_MONTHLY_INCOME)));
            userList.add(user);
        }
        return  userList;
    }

    //Adding users into the users table
    //public void addUser (User user){
    //    SQLiteDatabase db = this.getWritableDatabase(); //get writable database
    //    ContentValues values = new ContentValues(); //create content values to insert
    //    values.put(KEY_USER_NAME, user.getUsername()); //Put username in  @values
    //    values.put(KEY_PASSWORD, user.getPassword()); //Put password in  @values
    //    values.put(KEY_NAME, user.getName()); //Put name in  @values
    //    values.put(KEY_EMAIL, user.getEmail()); //Put email in  @values
    //    values.put(KEY_MONTHLY_INCOME, user.getMonthlyIncome()); //Put monthly income in  @values
    //    long todo_id = db.insert(TABLE_USERS, null, values); // insert row

    //}

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
