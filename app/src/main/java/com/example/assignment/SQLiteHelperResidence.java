package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelperResidence extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ResidenceDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_RESIDENCE ="residence";

    //TABLE USERS COLUMNS
    public static final String KEY_RESIDENCE_ID = "staffID"; // COLUMN Residence ID @primaryKey
    public static final String KEY_RESIDENCE_ADDRESS = "address";  //COLUMN address
    public static final String KEY_NUM_UNITS = "numUnits";//COLUMN number of units
    public static final String KEY_SIZE_PER_UNIT = "sizePerUnit";//COLUMN size per unit
    public static final String KEY_MONTHLY_RENTAL = "monthlyRental";//COLUMN monthly rental
    public static final String SQL_TABLE_RESIDENCE = " CREATE TABLE " + TABLE_RESIDENCE //SQL for creating userAdmin table
            + " ( "
            + KEY_RESIDENCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_RESIDENCE_ADDRESS + " TEXT,"
            + KEY_NUM_UNITS + " TEXT,"
            + KEY_SIZE_PER_UNIT + " TEXT,"
            + KEY_MONTHLY_RENTAL + " TEXT"
            + " ) ";


    public SQLiteHelperResidence(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_RESIDENCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_RESIDENCE);
    }

    public void addResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase(); //get writable database
        ContentValues values = new ContentValues(); //create content values to insert
        values.put(KEY_RESIDENCE_ADDRESS, residence.getAddress()); //Put address in  @values
        values.put(KEY_NUM_UNITS, residence.getNumOfUnits()); //Put password in  @values
        values.put(KEY_SIZE_PER_UNIT, residence.getSizePerUnit()); //Put name in  @values
        values.put(KEY_MONTHLY_RENTAL, residence.getMonthlyRental()); //Put email in  @values
        long todo_id = db.insert(TABLE_RESIDENCE, null, values); // insert row
    }
}
