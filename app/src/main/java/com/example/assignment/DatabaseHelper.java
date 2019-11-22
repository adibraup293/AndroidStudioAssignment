package com.example.assignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MicroHousingSystem";

    // Table Names
    private static final String TABLE_ALLOCATION = "allocation";
    private static final String TABLE_APPLICATIONS = "application";
    private static final String TABLE_RESIDENCE = "residence";
    private static final String TABLE_UNIT = "unit";
    private static final String TABLE_USERS = "users";

    // Common column names between tables
    //Common between Applications and Allocation table
    private static final String KEY_APPLICATION_ID = "applicationID";// COLUMN Application ID @primaryKey for Applications table
    private static final String KEY_DURATION = "duration";//COLUMN duration

    //Common between Residence, Unit and Applications table
    private static final String KEY_RESIDENCE_ID = "residenceID";// COLUMN Residence ID @primaryKey for Residence table

    //Common between Unit and Allocation table
    private static final String KEY_UNIT_ID = "unitID";//COLUMN unitID @primaryKey for Unit table

    //Common between Users, Applications and Residence tables
    private static final String KEY_USERNAME = "username";//COLUMN user name @primaryKey for Users table

    // Allocations Table - column names
    private static final String KEY_ALLOCATION_ID = "allocationID";//COLUMN Allocation ID @primaryKey
    private static final String KEY_FROM_DATE = "fromDate";//COLUMN fromDate

    // Applications Table - column names
    private static final String KEY_APPLICATION_DATE = "applicationDate";//COLUMN applicationDate
    private static final String KEY_REQUIRED_MONTH = "requiredMonth";//COLUMN required months
    private static final String KEY_REQUIRED_YEAR = "requiredYear";//COLUMN required year
    private static final String KEY_STATUS = "status";//COLUMN application status

    // Residence Table - column names
    private static final String KEY_RESIDENCE_NAME = "residenceName";//COLUMN address
    private static final String KEY_RESIDENCE_ADDRESS = "address";//COLUMN address
    private static final String KEY_NUM_UNITS = "numUnits";//COLUMN number of units
    private static final String KEY_SIZE_PER_UNIT = "sizePerUnit";//COLUMN size per unit
    private static final String KEY_MONTHLY_RENTAL = "monthlyRental";//COLUMN monthly rental

    // Unit Table - column names
    private static final String KEY_UNIT_NO = "unitNo";//COLUMN unitNo
    private static final String KEY_AVAILABILITY = "availability";//COLUMN availability

    // Users Table - column names
    private static final String KEY_USERTYPE = "usertype";//COLUMN user type
    private static final String KEY_PASSWORD = "password";//COLUMN password
    private static final String KEY_NAME = "name";//COLUMN name
    private static final String KEY_EMAIL = "email";//COLUMN email
    private static final String KEY_MONTHLY_INCOME = "monthlyIncome";//COLUMN monthly income

    //Table creation statements
    // Creating Allocation Table
    private static final String SQL_TABLE_ALLOCATION = " CREATE TABLE " + TABLE_ALLOCATION //SQL for creating allocations table
            + " ( "
            + KEY_ALLOCATION_ID + " INTEGER PRIMARY KEY, "
            + KEY_FROM_DATE + " DATE,"
            + KEY_DURATION + " TEXT,"
            + KEY_UNIT_ID + " INTEGER,"
            + KEY_APPLICATION_ID + " INTEGER"
            + " FOREIGN KEY (" + KEY_UNIT_ID + ") REFERENCES " + TABLE_UNIT + "(" + KEY_UNIT_ID + "));"
            + " FOREIGN KEY (" + KEY_APPLICATION_ID + ") REFERENCES " + TABLE_APPLICATIONS + "(" + KEY_APPLICATION_ID + "));";

    // Creating Applications Table
    private static final String SQL_TABLE_APPLICATIONS = " CREATE TABLE " + TABLE_APPLICATIONS //SQL for creating application table
            + " ( "
            + KEY_APPLICATION_ID + " INTEGER PRIMARY KEY, "
            + KEY_APPLICATION_DATE + " DATE,"
            + KEY_REQUIRED_MONTH + " TEXT,"
            + KEY_REQUIRED_YEAR + " TEXT,"
            + KEY_STATUS + " TEXT,"
            + KEY_USERNAME + " TEXT,"
            + KEY_RESIDENCE_ID + " INTEGER,"
            + KEY_UNIT_NO + " INTEGER,"
            + KEY_DURATION + " TEXT"
            + " FOREIGN KEY (" + KEY_USERNAME + ") REFERENCES " + TABLE_USERS + "(" + KEY_USERNAME + "));"
            + " FOREIGN KEY (" + KEY_RESIDENCE_ID + ") REFERENCES " + TABLE_RESIDENCE + "(" + KEY_RESIDENCE_ID + "));";

    // Creating Residence Table
    private static final String SQL_TABLE_RESIDENCE = " CREATE TABLE " + TABLE_RESIDENCE //SQL for creating residence table
            + " ( "
            + KEY_RESIDENCE_ID + " INTEGER PRIMARY KEY,"
            + KEY_RESIDENCE_NAME + " TEXT,"
            + KEY_RESIDENCE_ADDRESS + " TEXT,"
            + KEY_NUM_UNITS + " TEXT,"
            + KEY_SIZE_PER_UNIT + " TEXT,"
            + KEY_MONTHLY_RENTAL + " TEXT,"
            + KEY_USERNAME + " TEXT"
            + " FOREIGN KEY (" + KEY_USERNAME + ") REFERENCES " + TABLE_USERS + "(" + KEY_USERNAME + "));"
            + " ) ";

    // Creating Units table
    private static final String SQL_TABLE_UNIT = " CREATE TABLE " + TABLE_UNIT //SQL for creating unit table
            + " ( "
            + KEY_UNIT_ID + " INTEGER PRIMARY KEY,"
            + KEY_RESIDENCE_ID + " INTEGER,"
            + KEY_UNIT_NO + " INTEGER,"
            + KEY_AVAILABILITY + " TEXT"
            + " FOREIGN KEY (" + KEY_RESIDENCE_ID + ") REFERENCES " + TABLE_RESIDENCE + "(" + KEY_RESIDENCE_ID + "));";

    // Creating Users Table
    private static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS //SQL for creating users table
            + " ( "
            + KEY_USERNAME + " TEXT PRIMARY KEY, "
            + KEY_USERTYPE + " INTEGER,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT,"
            + KEY_MONTHLY_INCOME + " DOUBLE"
            + " ) ";

    //@adib Help me continue from this part onwards thanks man
    //Also help me check if I configured the database correctly

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
