package com.example.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.assignment.Admin.UserAdmin;
import com.example.assignment.Application.Applications;
import com.example.assignment.Residence.Residence;
import com.example.assignment.Unit.Unit;
import com.example.assignment.User.Applicant;
import com.example.assignment.User.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    //Common column names between tables
    //Common between Applications and Allocation table
    //private static final String KEY_APPLICATION_ID = "applicationID";// COLUMN Application ID @primaryKey for Applications table
    private static final String KEY_DURATION = "duration";//COLUMN duration

    //Common between Residence, Unit and Applications table
    //private static final String KEY_RESIDENCE_ID = "residenceID";// COLUMN Residence ID @primaryKey for Residence table

    //Common between Unit and Allocation table
    //private static final String KEY_UNIT_ID = "unitID";//COLUMN unitID @primaryKey for Unit table

    //Common between Users, Applications and Residence tables
    //private static final String KEY_USERNAME = "username";//COLUMN user name @primaryKey for Users table

    // Allocations Table - column names
    private static final String KEY_ALLOCATION_ID = "allocationID";//COLUMN Allocation ID @primaryKey
    private static final String KEY_FROM_DATE = "fromDate";//COLUMN fromDate
    private static final String KEY_FK_DURATION = "duration";//COLUMN duration
    private static final String KEY_FK_UNIT_ID = "unitID"; //COLUMN unitID
    private static final String KEY_FK_APPLICATION_ID = "applicationID";//COLUMN applicationID

    // Applications Table - column names
    private static final String KEY_APPLICATION_ID = "applicationID";
    private static final String KEY_APPLICATION_DATE = "applicationDate";//COLUMN applicationDate
    private static final String KEY_REQUIRED_MONTH = "requiredMonth";//COLUMN required months
    private static final String KEY_REQUIRED_YEAR = "requiredYear";//COLUMN required year
    private static final String KEY_STATUS = "status";//COLUMN application status
    private static final String KEY_APPLY_UNAME = "applicantUsername";//COLUMN application username
    private static final String KEY_FK_RESIDENCE_ID = "residenceID";//COLUMN residence ID
    private static final String KEY_FK_UNIT_NO = "unitNo";//COLUMN unit number
    private static final String KEY_FK_ADURATION = "application duration";//COLUMN duration

    // Residence Table - column names
    private static final String KEY_RESIDENCE_ID = "residenceID";//COLUMN residence ID
    private static final String KEY_RESIDENCE_NAME = "residenceName";//COLUMN address
    private static final String KEY_RESIDENCE_ADDRESS = "address";//COLUMN address
    private static final String KEY_NUM_UNITS = "numUnits";//COLUMN number of units
    private static final String KEY_SIZE_PER_UNIT = "sizePerUnit";//COLUMN size per unit
    private static final String KEY_MONTHLY_RENTAL = "monthlyRental";//COLUMN monthly rental
    private static final String KEY_FK_USERNAME = "username";//COLUMN username

    // Unit Table - column names
    private static final String KEY_UNIT_ID = "unitId";//COLUMN unitId
    private static final String KEY_FK_URESIDENCE_ID = "residenceId";//COLUMN residenceId
    private static final String KEY_UNIT_NO = "unitNo";//COLUMN unitNo
    private static final String KEY_AVAILABILITY = "availability";//COLUMN availability


    // Users Table - column names
    private static final String KEY_USERNAME = "username";//COLUMN user name
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
            + KEY_FK_DURATION + " TEXT,"
            + KEY_FK_UNIT_ID + " INTEGER,"
            + KEY_FK_APPLICATION_ID + " INTEGER"
            + " ) ";


    // Creating Applications Table
    private static final String SQL_TABLE_APPLICATIONS = " CREATE TABLE " + TABLE_APPLICATIONS //SQL for creating application table
            + " ( "
            + KEY_APPLICATION_ID + " INTEGER PRIMARY KEY, "
            + KEY_APPLICATION_DATE + " TEXT,"
            + KEY_REQUIRED_MONTH + " TEXT,"
            + KEY_REQUIRED_YEAR + " TEXT,"
            + KEY_STATUS + " TEXT DEFAULT \"New\","
            + KEY_APPLY_UNAME + " TEXT,"
            + KEY_FK_RESIDENCE_ID + " INTEGER,"
            + KEY_FK_UNIT_NO + " INTEGER,"
            + KEY_FK_ADURATION + "TEXT"
            + " ) ";


    // Creating Residence Table
    private static final String SQL_TABLE_RESIDENCE = " CREATE TABLE " + TABLE_RESIDENCE //SQL for creating residence table
            + " ( "
            + KEY_RESIDENCE_ID + " INTEGER PRIMARY KEY,"
            + KEY_RESIDENCE_NAME + " TEXT,"
            + KEY_RESIDENCE_ADDRESS + " TEXT,"
            + KEY_NUM_UNITS + " TEXT,"
            + KEY_SIZE_PER_UNIT + " TEXT,"
            + KEY_MONTHLY_RENTAL + " TEXT,"
            + KEY_FK_USERNAME + " TEXT"
            + " ) ";


    // Creating Units table
    private static final String SQL_TABLE_UNIT = " CREATE TABLE " + TABLE_UNIT //SQL for creating unit table
            + " ( "
            + KEY_UNIT_ID + " INTEGER PRIMARY KEY,"
            + KEY_FK_URESIDENCE_ID + " INTEGER,"
            + KEY_UNIT_NO + " INTEGER,"
            + KEY_AVAILABILITY + " TEXT"
            + " ) ";


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

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_ALLOCATION);
        sqLiteDatabase.execSQL(SQL_TABLE_APPLICATIONS);
        sqLiteDatabase.execSQL(SQL_TABLE_RESIDENCE);
        sqLiteDatabase.execSQL(SQL_TABLE_UNIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_ALLOCATION);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_APPLICATIONS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_RESIDENCE);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_UNIT);
    }
/*
    //checking if there are users in the database
    public User Authenticate(User user){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USERS, //selecting the users table
                new String[] {KEY_USERNAME, KEY_PASSWORD},
                KEY_USERNAME + "=?",
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
    }*/

    //Signing in
    public User loginUser(User user){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, //selecting the users table
                new String[] {KEY_USERNAME, KEY_PASSWORD},
                KEY_USERNAME + "=?",
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

    // Adding new Residence Details
    public void addResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_RESIDENCE_NAME, residence.getResidenceName());
        contentValues.put(KEY_RESIDENCE_ADDRESS, residence.getAddress());
        contentValues.put(KEY_NUM_UNITS, residence.getNumOfUnits());
        contentValues.put(KEY_SIZE_PER_UNIT, residence.getSizePerUnit());
        contentValues.put(KEY_MONTHLY_RENTAL, residence.getMonthlyRental());

        db.insert(TABLE_RESIDENCE, null, contentValues);
        db.close();

    }

    public void addUnit(Unit unit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_AVAILABILITY, unit.getAvailability());

        db.insert(TABLE_UNIT, null, contentValues);
        db.close();
    }

    /*
        public Residence GetResidence(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]
                        {Util.KEY_RESIDENCE_ID, Util.KEY_ADDRESS, Util.KEY_NUMOFUNITS,
                                KEY_SIZEPERUNIT, Util.KEY_MONTHLYRENTAL},
                KEY_RESIDENCE_ID+ "=?", new String[]{String.valueOf(id)},
                null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Residence residence = new Residence();
        residence.setResidenceid((Integer.parseInt(cursor.getString(0))));
        residence.setAddress(cursor.getString(1));
        residence.setNumOfUnit(cursor.getString(2));
        residence.setSizePerUnit(cursor.getString(3));
        residence.setMonthlyRental(cursor.getString(4));

        return residence;
    }
     */

    /*
        + KEY_APPLICATION_ID + " INTEGER PRIMARY KEY, "
            + KEY_APPLICATION_DATE + " DATE,"
            + KEY_REQUIRED_MONTH + " TEXT,"
            + KEY_REQUIRED_YEAR + " TEXT,"
            + KEY_STATUS + " TEXT DEFAULT \"New\","
            + KEY_APPLY_UNAME + " TEXT,"
            + KEY_FK_RESIDENCE_ID + " INTEGER,"
            + KEY_FK_UNIT_NO + " INTEGER,"
            + KEY_FK_ADURATION + "TEXT"
     */

    public Applications getApplicantsApplication(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_APPLICATIONS,
                new String[]{KEY_APPLICATION_ID,
                    KEY_APPLICATION_DATE,
                    KEY_REQUIRED_MONTH,
                        KEY_REQUIRED_YEAR,
                        KEY_STATUS,
                        KEY_APPLY_UNAME},
                KEY_APPLICATION_ID + "=?",
                new String[]{String.valueOf(id)},
                null,null,null);

        if (cursor != null){
            cursor.moveToFirst(); }

        Applications applications = new Applications();
        applications.setApplicationID(Integer.parseInt(cursor.getString(0)));
        applications.setApplicationDate(cursor.getString(1));
        applications.setRequiredMonth(cursor.getString(2));
        applications.setStatus(cursor.getString(3));

        db.close();
        return applications;
    }


    public Residence getResidence(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RESIDENCE,
                new String[]{KEY_RESIDENCE_ID,
                        KEY_RESIDENCE_NAME,
                        KEY_RESIDENCE_ADDRESS,
                        KEY_NUM_UNITS,
                        KEY_SIZE_PER_UNIT,
                        KEY_MONTHLY_RENTAL,
                KEY_FK_USERNAME},
                KEY_RESIDENCE_ID + "=?",
                new String[]{String.valueOf(id)},
                null,null,null);
        if (cursor != null){
            cursor.moveToFirst();}

        Residence residence = new Residence();
        residence.setResidenceID(Integer.parseInt(cursor.getString(0)));
        residence.setResidenceName(cursor.getString(1));
        residence.setAddress(cursor.getString(2));
        residence.setNumOfUnits(Integer.parseInt(cursor.getString(3)));
        residence.setSizePerUnit(Integer.parseInt(cursor.getString(4)));
        residence.setMonthlyRental(Double.parseDouble(cursor.getString(5)));
        db.close();
        return residence;
    }

    public ArrayList<Applications> getAllApplicationByResID(int id){
        ArrayList<Applications> applicationsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + TABLE_APPLICATIONS + " WHERE " + KEY_RESIDENCE_ID +
                " =? ";

        Cursor cursor = db.rawQuery(selectAll, new String[]{Integer.toString(id)});
        if (cursor.moveToFirst()){
            do {
                Applications applications = new Applications();
                applications.setApplicationID(Integer.parseInt(cursor.getString(0)));
                applications.setStatus(cursor.getString(1));
                applications.setRequiredMonth(cursor.getString(2));
                applications.setApplicationDate(cursor.getString(3));
                applicationsList.add(applications);

            }while (cursor.moveToNext());
        }
        db.close();
        return  applicationsList;

    }

    public void WithdrawApplication(){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteAll = "DELETE FROM " + TABLE_APPLICATIONS;

        db.execSQL(deleteAll);
        db.close();
    }

    public List<Applications> GetAllApplications(){
        List<Applications> applicationList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM " + TABLE_APPLICATIONS;
        Cursor cursor = db.rawQuery(selectAll, null);
        if(cursor.moveToFirst() == true){

            do{
                Applications applications = new Applications();
                applications.setApplicationID((Integer.parseInt(cursor.getString(0))));
                applications.setApplicationDate((cursor.getString(1)));
                applications.setRequiredMonth((cursor.getString(2)));
                applications.setRequiredYear((Integer.parseInt(cursor.getString(3))));
                applications.setStatus((cursor.getString(4)));

                applicationList.add(applications);

            }while (cursor.moveToNext());
        }
        db.close();
        return applicationList;
    }

    public List<Residence> GetAllResidences(){

        List<Residence> residenceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + TABLE_RESIDENCE;

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

    public void DeleteResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_RESIDENCE,
                KEY_RESIDENCE_ID + "=?",
                new String[]{String.valueOf(residence.getResidenceID())});
        db.close();
    }


    public int UpdateResidence(Residence residence){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_RESIDENCE_ADDRESS, residence.getAddress());
        contentValues.put(KEY_NUM_UNITS, residence.getNumOfUnits());
        contentValues.put(KEY_SIZE_PER_UNIT, residence.getSizePerUnit());
        contentValues.put(KEY_MONTHLY_RENTAL, residence.getMonthlyRental());

        return db.update(TABLE_RESIDENCE,contentValues,
                KEY_RESIDENCE_ID + "=?",
                new String[]{String.valueOf(residence.getResidenceID())});
    }


    // Adding new User Details
    public void AddAdminUser(UserAdmin admin) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, admin.getUsername());
        contentValues.put(KEY_USERTYPE, 0);
        contentValues.put(KEY_PASSWORD, admin.getPassword());
        contentValues.put(KEY_NAME, admin.getName());

        long newRowId = db.insert(TABLE_USERS, null, contentValues);
        db.close();
    }

    // Adding new User Details
    public void AddApplicantUser(Applicant applicant) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, applicant.getUsername());
        contentValues.put(KEY_USERTYPE, 1);
        contentValues.put(KEY_PASSWORD, applicant.getPassword());
        contentValues.put(KEY_NAME, applicant.getName());
        contentValues.put(KEY_EMAIL, applicant.getEmail());
        contentValues.put(KEY_MONTHLY_INCOME, applicant.getMonthlyIncome());

        long newRowId = db.insert(TABLE_USERS, null, contentValues);
        db.close();
    }

/*    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_USERNAME, KEY_PASSWORD},//Selecting columns want to query
                KEY_USERNAME + "=?",
                new String[]{user.getUsername()},//Where clause
                null, null, null);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given username
            User user1 = new User(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2));
            //Match both passwords check they are same or not
            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }
        //if user password does not matches or there is no record with that email then return @false
        return null;
    }*/

    private static Date date = new Date();
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "MM/dd/yyyy", Locale.ENGLISH);
    private static String currentDate = formatter.format(date);

    // Adding new Application Details
    public void addApplications(Applications applications){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_APPLICATION_DATE, currentDate);
        contentValues.put(KEY_REQUIRED_MONTH, applications.getRequiredMonth());
        contentValues.put(KEY_REQUIRED_YEAR, applications.getRequiredYear());
        contentValues.put(KEY_STATUS, applications.getStatus());
        db.insert(TABLE_APPLICATIONS, null, contentValues);
        db.close();
    }

    /*
    public List<Applications> getApplicationsForUser() {
        List<Applications> applicationsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String getAll = "SELECT * FROM " + TABLE_APPLICATIONS + " WHERE username LIKE '" + +"'";
    }/*

    /*
    public List<Residence> GetAllResidences(){

        List<Residence> residenceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + TABLE_RESIDENCE;

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
    }*/

    /*
        public void AddResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ADDRESS, residence.getAddress());
        contentValues.put(KEY_NUMOFUNITS, residence.getNumOfUnit());
        contentValues.put(KEY_SIZEPERUNIT, residence.getSizePerUnit());
        contentValues.put(KEY_MONTHLYRENTAL, residence.getMonthlyRental());


        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    public Residence GetResidence(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]
                        {Util.KEY_RESIDENCE_ID, Util.KEY_ADDRESS, Util.KEY_NUMOFUNITS,
                                KEY_SIZEPERUNIT, Util.KEY_MONTHLYRENTAL},
                KEY_RESIDENCE_ID+ "=?", new String[]{String.valueOf(id)},
                null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Residence residence = new Residence();
        residence.setResidenceid((Integer.parseInt(cursor.getString(0))));
        residence.setAddress(cursor.getString(1));
        residence.setNumOfUnit(cursor.getString(2));
        residence.setSizePerUnit(cursor.getString(3));
        residence.setMonthlyRental(cursor.getString(4));

        return residence;
    }
    public List<Residence> GetAllResidences(){
        List<Residence> residenceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()){
            do{
                Residence residence = new Residence();
                residence.setResidenceid((Integer.parseInt(cursor.getString(0))));
                residence.setAddress(cursor.getString(1));
                residence.setNumOfUnit(cursor.getString(2));
                residence.setSizePerUnit(cursor.getString(3));
                residence.setMonthlyRental(cursor.getString(4));

                residenceList.add(residence);
            }while (cursor.moveToNext());
        }
        db.close();
        return residenceList;
    }

    public void DeleteAllResidences(){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteAll = "DELETE FROM " + Util.TABLE_NAME;

        db.execSQL(deleteAll);
        db.close();
    }

    public void DeleteResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, KEY_RESIDENCE_ID + "=?",
                new String[]{String.valueOf(residence.getResidenceid())});
        db.close();
    }

    public void WithdrawApplication(){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteAll = "DELETE FROM " + TABLE_Application;

        db.execSQL(deleteAll);
        db.close();
    }

    public int UpdateResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_ADDRESS, residence.getAddress());
        contentValues.put(Util.KEY_NUMOFUNITS, residence.getNumOfUnit());
        contentValues.put(KEY_SIZEPERUNIT, residence.getSizePerUnit());
        contentValues.put(Util.KEY_MONTHLYRENTAL, residence.getMonthlyRental());

        return db.update(Util.TABLE_NAME,contentValues,
                Util.KEY_RESIDENCE_ID+"=?",
                new String[]{String.valueOf(residence.getResidenceid())});
    }

    public void CreateApplication(Application application) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(APPLICATION_DATE, application.date);
        values.put(APPLICATION_MONTH, application.month);
        values.put(APPLICATION_YEAR, application.year);

        // Inserting Row
        db.insert(TABLE_Application, null, values);
        db.close();
    }



    public Application GetApplication(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Application,
                new String[]{Util.APPLICATION_ID,
                        Util.APPLICATION_DATE,  Util.APPLICATION_MONTH,
                        Util.APPLICATION_YEAR,  Util.APPLICATION_STATUS,  Util.APPLICATION_PAYSLIP},
                Util.APPLICATION_ID + "=?", new String[]{String.valueOf(id)},
                null,null,null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Application application = new Application();
        application.setApplicationid((cursor.getString(0)));
        application.setDate((cursor.getString(1)));
        application.setMonth((cursor.getString(2)));
        application.setYear((cursor.getString(3)));
        application.setStatus((cursor.getString(4)));
        db.close();
        return application;


    }

     */

}