package com.example.assignment.Residence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelperResidence extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ResidenceDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_RESIDENCE ="residence";

    //TABLE RESIDENCE COLUMNS
    public static final String KEY_RESIDENCE_ID = "residenceID"; // COLUMN Residence ID @primaryKey
    public static final String KEY_RESIDENCE_ADDRESS = "address";  //COLUMN address
    public static final String KEY_NUM_UNITS = "numUnits";//COLUMN number of units
    public static final String KEY_SIZE_PER_UNIT = "sizePerUnit";//COLUMN size per unit
    public static final String KEY_MONTHLY_RENTAL = "monthlyRental";//COLUMN monthly rental
    public static final String SQL_TABLE_RESIDENCE = " CREATE TABLE " + TABLE_RESIDENCE //SQL for creating residence table
            + " ( "
            + KEY_RESIDENCE_ID + " INTEGER PRIMARY KEY, "
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

    // Adding new Residence Details
    public void addResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelperResidence.KEY_RESIDENCE_ADDRESS, residence.getAddress());
        contentValues.put(SQLiteHelperResidence.KEY_NUM_UNITS, residence.getNumOfUnits());
        contentValues.put(SQLiteHelperResidence.KEY_SIZE_PER_UNIT, residence.getSizePerUnit());
        contentValues.put(SQLiteHelperResidence.KEY_MONTHLY_RENTAL, residence.getMonthlyRental());

        db.insert(SQLiteHelperResidence.TABLE_RESIDENCE, null, contentValues);
        db.close();

    }

    public Residence getResidence(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(SQLiteHelperResidence.TABLE_RESIDENCE,
                new String[]{SQLiteHelperResidence.KEY_RESIDENCE_ID,
                        SQLiteHelperResidence.KEY_RESIDENCE_ADDRESS,
                        SQLiteHelperResidence.KEY_NUM_UNITS,
                        SQLiteHelperResidence.KEY_SIZE_PER_UNIT,
                        SQLiteHelperResidence.KEY_MONTHLY_RENTAL},
                        SQLiteHelperResidence.KEY_RESIDENCE_ID + "=?",
                        new String[]{String.valueOf(id)},
                null,null,null);
                if (cursor != null){
                    cursor.moveToFirst();}

                Residence residence = new Residence();
                residence.setResidenceID(Integer.parseInt(cursor.getString(0)));
                residence.setAddress(cursor.getString(1));
                residence.setNumOfUnits(Integer.parseInt(cursor.getString(2)));
                residence.setSizePerUnit(Integer.parseInt(cursor.getString(3)));
                residence.setMonthlyRental(Double.parseDouble(cursor.getString(4)));
                db.close();
                return residence;
    }

    //void insertResidence(String address, int numOfUnits, int sizePerUnit, Double monthlyRental){
    //    //Get the Data Repository in write mode
    //    SQLiteDatabase db = this.getWritableDatabase();
    //    //Create a new map of values, where column names are the keys
    //    ContentValues cValues = new ContentValues();
    //    cValues.put(KEY_RESIDENCE_ADDRESS, address);
    ///    cValues.put(KEY_NUM_UNITS, numOfUnits);
    //    cValues.put(KEY_SIZE_PER_UNIT, sizePerUnit);
    //    cValues.put(KEY_MONTHLY_RENTAL, monthlyRental);
    //    // Insert the new row, returning the primary key value of the new row
    //    long newRowId = db.insert(TABLE_RESIDENCE,null, cValues);
    //}

    public List<Residence> GetAllResidences(){

        List<Residence> residenceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + SQLiteHelperResidence.TABLE_RESIDENCE;

        Cursor cursor = db.rawQuery(selectAll,null);

        if (cursor.moveToFirst()){
            do {
                Residence residence = new Residence();
                residence.setResidenceID(Integer.parseInt(cursor.getString(0)));
                residence.setAddress(cursor.getString(1));
                residence.setNumOfUnits(cursor.getInt(2));
                residence.setSizePerUnit(cursor.getInt(3));
                residence.setMonthlyRental(cursor.getDouble(4));

                residenceList.add(residence);

            }while (cursor.moveToNext());
        }
        db.close();
        return  residenceList;
    }

    // Get User Details
    //public ArrayList<HashMap<String, String>> getResidence(){
    //    SQLiteDatabase db = this.getWritableDatabase();
    //    ArrayList<HashMap<String, String>> residenceList = new ArrayList<>();
    //    String query = "SELECT address, numUnits, sizePerUnit, monthlyRental FROM "+ TABLE_RESIDENCE;
    //    Cursor cursor = db.rawQuery(query,null);
     ///   while (cursor.moveToNext()){
     //       HashMap<String,String> residence = new HashMap<>();
     //       residence.put("address",cursor.getString(cursor.getColumnIndex(KEY_RESIDENCE_ADDRESS)));
     //       residence.put("numUnits",cursor.getString(cursor.getColumnIndex(KEY_NUM_UNITS)));
     //       residence.put("sizePerUnit",cursor.getString(cursor.getColumnIndex(KEY_SIZE_PER_UNIT)));
      //      residence.put("monthlyRental",cursor.getString(cursor.getColumnIndex(KEY_MONTHLY_RENTAL)));
//
      //      residenceList.add(residence);
      //  }
     //   return  residenceList;
   // }

    public void DeleteAllResidence(){

        SQLiteDatabase db = this.getWritableDatabase();
        String deleteAll = "DELETE FROM " + SQLiteHelperResidence.TABLE_RESIDENCE;

        db.execSQL(deleteAll);
        db.close();
    }

    public void DeleteResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(SQLiteHelperResidence.TABLE_RESIDENCE,
                SQLiteHelperResidence.KEY_RESIDENCE_ID + "=?",
                new String[]{String.valueOf(residence.getResidenceID())});
        db.close();
    }

    public int UpdateResidence(Residence residence){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelperResidence.KEY_RESIDENCE_ADDRESS, residence.getAddress());
        contentValues.put(SQLiteHelperResidence.KEY_NUM_UNITS, residence.getNumOfUnits());
        contentValues.put(SQLiteHelperResidence.KEY_SIZE_PER_UNIT, residence.getSizePerUnit());
        contentValues.put(SQLiteHelperResidence.KEY_MONTHLY_RENTAL, residence.getMonthlyRental());

        return db.update(SQLiteHelperResidence.TABLE_RESIDENCE,contentValues,
                SQLiteHelperResidence.KEY_RESIDENCE_ID + "=?",
                new String[]{String.valueOf(residence.getResidenceID())});
    }


    //public List<Residence> getAllResidence(){
    //   List<Residence> residenceList = new ArrayList<>();
    //    SQLiteDatabase db = this.getReadableDatabase();
    //    String selectAll = "SELECT * FROM " +DATABASE_NAME;

    //    Cursor cursor = db.rawQuery(selectAll,null);

    //    if (cursor.moveToFirst()){
    //        do {
    //            Residence residence = new Residence();
    //            residence.setAddress(cursor.getString(1));
     //           residence.setNumOfUnits(cursor.getInt(2));
     //           residence.setSizePerUnit(cursor.getInt(3));
     //           residence.setMonthlyRental(cursor.getDouble(4));

      //          residenceList.add(residence);
      //      }while (cursor.moveToNext());
       // }
    //    db.close();
    //    return residenceList;
    //}
}
