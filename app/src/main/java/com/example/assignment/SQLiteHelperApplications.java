package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SQLiteHelperApplications extends SQLiteOpenHelper {

    private static Date date = new Date();
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "MM/dd/yyyy", Locale.ENGLISH);
    private static String currentDate = formatter.format(date);

    public static final String DATABASE_NAME = "ApplicationsDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_APPLICATIONS ="application";


    //TABLE APPLICATIONS COLUMNS
    public static final String KEY_Applications_ID = "applicationID"; // COLUMN Application ID @primaryKey
    public static final String KEY_APPLICATION_DATE = "applicationDate";  //COLUMN applicationDate
    public static final String KEY_REQUIRED_MONTHS = "requiredMonths";//COLUMN required months
    public static final String KEY_REQUIRED_YEAR = "requiredYear";//COLUMN required year
    public static final String KEY_STATUS = "status";//COLUMN application status
    public static final String KEY_USERNAME = "applyUsername";//COLUMN username who applied
    public static final String KEY_RESIDENCE_ID = "residenceID";//COLUMN residenceID
    public static final String SQL_TABLE_APPLICATIONS = " CREATE TABLE " + TABLE_APPLICATIONS //SQL for creating residence table //SQL for creating application table
            + " ( "
            + KEY_Applications_ID + " INTEGER PRIMARY KEY, "
            + KEY_APPLICATION_DATE + " DATE,"
            + KEY_REQUIRED_MONTHS + " TEXT,"
            + KEY_REQUIRED_YEAR + " TEXT,"
            + KEY_STATUS + " TEXT,"
            + KEY_USERNAME + " TEXT,"
            + KEY_RESIDENCE_ID + " TEXT"
            + " FOREIGN KEY ("+KEY_USERNAME+") REFERENCES "+ SQLiteHelperUser.TABLE_USERS + "("+SQLiteHelperUser.KEY_USER_NAME+"));"
            + " FOREIGN KEY ("+KEY_RESIDENCE_ID+") REFERENCES "+ SQLiteHelperResidence.TABLE_RESIDENCE + "("+SQLiteHelperResidence.KEY_RESIDENCE_ID+"));";

    public SQLiteHelperApplications(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_APPLICATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + SQL_TABLE_APPLICATIONS);
    }

    // Adding new Application Details
    public void addApplications(Applications applications){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelperApplications.KEY_APPLICATION_DATE, formatter.format(applications.getApplicationDate()));
        contentValues.put(SQLiteHelperApplications.KEY_REQUIRED_MONTHS, applications.getRequiredMonth());
        contentValues.put(SQLiteHelperApplications.KEY_REQUIRED_YEAR, applications.getRequiredYear());
        contentValues.put(SQLiteHelperApplications.KEY_STATUS, applications.getStatus());
        db.insert(SQLiteHelperApplications.TABLE_APPLICATIONS, null, contentValues);
        db.close();

    }

    public List<Applications> GetAllApplications(){


        List<Applications> applicationsList = new ArrayList<>();
        //String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";


        //db.rawQuery(MY_QUERY, new String[]{String.valueOf(propertyId)});

        SQLiteDatabase db = this.getReadableDatabase();

        String applicationQuery = "SELECT * FROM " + TABLE_APPLICATIONS + " a "
                + " INNER JOIN " + SQLiteHelperResidence.TABLE_RESIDENCE + " r "
                + " ON a." + KEY_RESIDENCE_ID + " = r." + SQLiteHelperResidence.KEY_RESIDENCE_ID + "AND";


        Cursor cursor = db.rawQuery(applicationQuery,null);

        if (cursor.moveToFirst()){
            do {
                Applications applications = new Applications();
                User user = new User();
                Residence residence = new Residence();

                applications.setApplicationID(Integer.parseInt(cursor.getString(0)));
                applications.setApplicationDate(cursor.getString(1));
                applications.setRequiredMonth(cursor.getString(2));
                applications.setRequiredYear(cursor.getInt(3));
                applications.setStatus(cursor.getString(4));
                user.setUsername(cursor.getString(5));
                residence.setResidenceID(Integer.parseInt(cursor.getString(6)));

                applicationsList.add(applications);
                //applicationsList.add(user);
                //residenceList.add(residence);

            }while (cursor.moveToNext());
        }
        db.close();
        return  applicationsList;
    }
}
