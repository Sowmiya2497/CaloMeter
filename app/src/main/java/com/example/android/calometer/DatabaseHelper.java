package com.example.android.calometer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


//import static android.R.attr.key;
//import static com.example.android.calometer.data.AppContract.UserEntry.TABLE_NAME;

/**
 * Created by subrafive on 02-09-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "calometer.db";

    public static final int DATABASE_VERSION = 1;
    public final static String TABLE_NAME1 = "user_info";
    public final static String COLUMN_ID = "id";
    public final static String COLUMN_USER_NAME = "username";
    public final static String COLUMN_USER_PASSWORD = "password";

    public final static String TABLE_NAME2 = "target_details";
    public final static String COLUMN_USER_ID = "user_id";
    public final static String COLUMN_CURRENT_WEIGHT = "current_wt";
    public final static String COLUMN_TARGET_WEIGHT = "target_wt";
    public final static String COLUMN_TARGET_DAYS = "target_days";
    public final static String COLUMN_START_DATE = "start_date";
    public final static String COLUMN_CALORIES_PER_DAY = "calories_per_day";

    public final static String TABLE_NAME3 = "food";
    public final static String COLUMN_FOOD_ID = "food_id";
    public final static String COLUMN_FOOD_NAME = "food_name";
    public final static String COLUMN_CALORIES = "calories";
    public final static String COLUMN_UNIT = "unit";

    public final static String TABLE_NAME4 = "user_food";
    public final static String COLUMN_USER_FOOD_ID = "_id";
    public final static String COLUMN_USER_FOOD_USER_ID = "user_id";
    public final static String COLUMN_USER_FOOD_FOOD_NAME = "food_name";
    public final static String COLUMN_USER_FOOD_CALORIES = "calories";
    public final static String COLUMN_USER_FOOD_AMOUNT = "amount";

    public final static String TABLE_NAME5 = "exercise";
    public final static String COLUMN_EX_ID = "ex_id";
    public final static String COLUMN_EX_NAME = "ex_name";
    public final static String COLUMN_EX_CALORIES = "calories";

    public final static String TABLE_NAME6 = "user_ex";
    public final static String COLUMN_USER_EX_ID = "_id";
    public final static String COLUMN_USER_EX_USER_ID = "user_id";
    public final static String COLUMN_USER_EX_EX_NAME = "ex_name";
    public final static String COLUMN_USER_EX_CALORIES = "calories";
    public final static String COLUMN_USER_EX_TIME = "time";

    public final static String TABLE_NAME7 = "dailytracker";
    public final static String COLUMN_DAILY_ID = "_id";
    public final static String COLUMN_DAILY_USER_ID = "user_id";
    public final static String COLUMN_DAILY_DAY = "day";
    public final static String COLUMN_DAILY_CARRY_OVER = "carry";

    SQLiteDatabase db;

    public static final String TABLE1_CREATE = "create table user_info(id primary key not null,"+
            "username text not null unique,password text not null);";

    public static final String TABLE2_CREATE = "create table target_details(user_id integer primary key," +
            "current_wt integer not null, target_wt integer not null, target_days integer not null, start_date date not null, calories_per_day integer not null, " +
            "foreign key(user_id) references user_info(id));";

    public static final String TABLE3_CREATE = "create table food(food_id integer primary key," +
            "food_name text not null,calories integer not null,unit text not null);";


    public static final String TABLE4_CREATE = "create table user_food(_id integer primary key, user_id integer references user_info(id)," +
            "food_name text not null references food(food_name),amount int not null,calories int not null);";


    public static final String TABLE5_CREATE = "create table exercise(ex_id integer primary key," +
            "ex_name text not null,calories integer not null,unit text not null);";


    public static final String TABLE6_CREATE = "create table user_ex(_id integer primary key, user_id integer references user_info(id)," +
            "ex_name text not null references exercise(ex_name),time int not null,calories integer not null);";

    public static final String TABLE7_CREATE = "create table dailytracker(_id integer primary key, user_id integer references user_info(id)," +
            "day date not null,carry integer not null);";



    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public int insertUser(Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from user_info;";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_USER_NAME,c.getName());
        values.put(COLUMN_USER_PASSWORD,c.getPassword());
        db.insert(TABLE_NAME1,null,values);
        return count;
    }

    public int insertFoodItem(FoodItem foo)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from user_food;";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_USER_FOOD_ID,count);
        values.put(COLUMN_USER_FOOD_USER_ID,foo.getUserId());
        values.put(COLUMN_USER_FOOD_FOOD_NAME,foo.getFoodName());
        values.put(COLUMN_USER_FOOD_AMOUNT,foo.getAmount());
        values.put(COLUMN_USER_FOOD_CALORIES,foo.getCalories());
        db.insert(TABLE_NAME4,null,values);
        return 1;
    }

    public void insertUserTrack(int id,int carry)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from dailytracker;";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        values.put(COLUMN_DAILY_ID,count);
        values.put(COLUMN_DAILY_USER_ID,id);
        values.put(COLUMN_DAILY_DAY,date);
        values.put(COLUMN_DAILY_CARRY_OVER,carry);
        db.insert(TABLE_NAME7,null,values);

    }

    public int[] getDailyCalories(int id)
    {
        int[] calories = new int[50];int i=0;
        db = this.getReadableDatabase();
        String query = "select carry from dailytracker where user_id = "+id+";";
        Cursor cur = db.rawQuery(query,null);
        if(cur!=null && cur.moveToFirst()) {
            while(cur.moveToNext())
            {
                calories[i] = Integer.parseInt(cur.getString(cur.getColumnIndex(COLUMN_DAILY_CARRY_OVER)));
                i++;
            }
        }
        return calories;

    }

    public int getRecordCount(int id)
    {
        int count = 0;
        db = this.getReadableDatabase();
        String query = "select count(*) as ct from dailytracker where user_id = "+id+";";
        Cursor cur = db.rawQuery(query,null);
        if(cur!=null && cur.moveToFirst()) {
            count = Integer.parseInt(cur.getString(cur.getColumnIndex("ct")));
        }
        return count;
    }

    public int checkLastDate(int id)
    {
        int flag=0,tdays=0;
        db = this.getReadableDatabase();
        String sdate = null;
        String query = "select start_date, target_days from " + TABLE_NAME2 + " where user_id = " + id + ";";
        Cursor cur = db.rawQuery(query,null);
        if(cur!=null && cur.moveToFirst()) {
            sdate = cur.getString(cur.getColumnIndex(COLUMN_START_DATE));
            tdays = Integer.parseInt(cur.getString(cur.getColumnIndex(COLUMN_TARGET_DAYS)));
        }
          // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(sdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, tdays);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
        String check_date = sdf1.format(c.getTime());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if(check_date.contentEquals(date))
        {
            flag = 1;
        }
        return flag;
    }


    public int insertExItem(ExItem ex_item)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from user_ex;";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_USER_EX_ID,count);
        values.put(COLUMN_USER_EX_USER_ID,ex_item.getUserId());
        values.put(COLUMN_USER_EX_EX_NAME,ex_item.getExName());
        values.put(COLUMN_USER_EX_TIME,ex_item.getTime());
        values.put(COLUMN_USER_EX_CALORIES,ex_item.getExCalories());
        db.insert(TABLE_NAME6,null,values);
        return 1;
    }

    public int getCalories(String food_name)
    {
        int cal = -1;
        db = this.getReadableDatabase();
        String query = "SELECT * from " + TABLE_NAME3 + " where food_name = \"" + food_name + "\";";
        Cursor cur = db.rawQuery(query,null);
        if(cur!=null && cur.moveToFirst()) {
            cal = Integer.parseInt(cur.getString(cur.getColumnIndex(COLUMN_EX_CALORIES)));
        }
        return cal;
    }

    public int getExCalories(String ex_name)
    {
        int cal = -1;
        db = this.getReadableDatabase();
        String query = "SELECT * from " + TABLE_NAME5 + " where ex_name = \"" + ex_name + "\";";
        Cursor cur = db.rawQuery(query,null);
        if(cur!=null && cur.moveToFirst()) {
            cal = Integer.parseInt(cur.getString(cur.getColumnIndex(COLUMN_CALORIES)));
        }
        return cal;
    }

    public Cursor getAllRows(int id)
    {
        db = this.getReadableDatabase();
        String query = "SELECT * from " + TABLE_NAME4 + " where user_id = \"" + id + "\";";
        Cursor cur = db.rawQuery(query,null);
        return cur;
    }

    public Cursor getDropDownFood()
    {
        db = this.getReadableDatabase();
        String query = "SELECT food_name from " + TABLE_NAME3 + ";";
        Cursor cur = db.rawQuery(query,null);
        return cur;
    }

    public Cursor getDropDownEx()
    {
        db = this.getReadableDatabase();
        String query = "SELECT ex_name from " + TABLE_NAME5 + ";";
        Cursor cur = db.rawQuery(query,null);
        return cur;
    }

    public Cursor getExRows(int id)
    {
        db = this.getReadableDatabase();
        String query = "SELECT * from " + TABLE_NAME6 + " where user_id = \"" + id + "\";";
        Cursor cur = db.rawQuery(query,null);
        return cur;
    }
    public int checkLogin(String uname, String upass){
        int val = -1;
        db = this.getReadableDatabase();
        String query = "SELECT * from " + TABLE_NAME1 + " where username = \"" + uname + "\" and password = \"" + upass + "\";";
        Cursor cur = db.rawQuery(query,null);
        if( cur != null && cur.moveToFirst() ) {
             val = Integer.parseInt(cur.getString(cur.getColumnIndex("id")));
        }
        return val;
    }


    public int insertDetails(target_details td){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, td.getUser_id());
        values.put(COLUMN_TARGET_WEIGHT, td.getTarget_wt());
        values.put(COLUMN_CURRENT_WEIGHT, td.getCurrent_wt());
        values.put(COLUMN_TARGET_DAYS, td.getTarget_days());
        values.put(COLUMN_START_DATE, td.getStartDate());
        values.put(COLUMN_CALORIES_PER_DAY, td.getCalories_per_day());
        db.insert(TABLE_NAME2,null,values);
        return 1;
    }

    public int foodCalsBurnt(int id){
        int total_food_cals = 0;
        db = this.getReadableDatabase();
        String query = "SELECT sum(calories) as cal from "+TABLE_NAME4+" where user_id = "+id+";";
        Cursor cur = db.rawQuery(query,null);
        if( cur != null && cur.moveToFirst() ) {
            if ((cur.getString(cur.getColumnIndex("cal")) != null)) {
                total_food_cals = Integer.parseInt(cur.getString(cur.getColumnIndex("cal")));
            }
        }

        return total_food_cals;
    }

    public int exerciseCalsBurnt(int id){
        int total_ex_cals = 0;
        db = this.getReadableDatabase();
        String query = "SELECT sum(calories) as cal from "+TABLE_NAME6+" where user_id = "+id+";";
        Cursor cur = db.rawQuery(query,null);
        if( cur != null && cur.moveToFirst() ) {
            if((cur.getString(cur.getColumnIndex("cal"))!=null)) {
                total_ex_cals = Integer.parseInt(cur.getString(cur.getColumnIndex("cal")));
            }

        }

        return total_ex_cals;
    }

    public String getUnit(String foodname)
    {
        String unit="";
        db = this.getReadableDatabase();
        String query = "SELECT unit FROM "+TABLE_NAME3+" where food_name like '"+foodname+"%';";
        Cursor cur = db.rawQuery(query,null);
        if( cur != null && cur.moveToFirst() ) {
           unit = cur.getString(cur.getColumnIndex("calories_per_day"));
        }
        return unit;
    }

    public int returnTarget(int id)
    {
        int target = 0;
        db = this.getReadableDatabase();
        String query = "SELECT calories_per_day FROM "+TABLE_NAME2+" where user_id = "+id+";";
        Cursor cur = db.rawQuery(query,null);
        if( cur != null && cur.moveToFirst() ) {
            target = Integer.parseInt(cur.getString(cur.getColumnIndex("calories_per_day")));
        }
        return target;
    }

    public void deleteUserFood(int id)
    {
        db = this.getWritableDatabase();
        String query = "DELETE FROM "+TABLE_NAME4+" where user_id = "+id+";";
        db.execSQL(query);
    }

    public void deleteExercise(int id)
    {
        db = this.getWritableDatabase();
        String query = "DELETE FROM "+TABLE_NAME6+" where user_id = "+id+";";
        db.execSQL(query);
    }

    public void updateTarget(int id,int newTarget)
    {
        db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME2+" SET calories_per_day ="+newTarget+" where user_id = "+id+";";
        db.execSQL(query);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE1_CREATE);
        db.execSQL(TABLE2_CREATE);
        db.execSQL(TABLE3_CREATE);
        db.execSQL(TABLE4_CREATE);
        db.execSQL(TABLE5_CREATE);
        db.execSQL(TABLE6_CREATE);
        db.execSQL(TABLE7_CREATE);
        String q1 = "insert into food values(1,\"Apple(in no.s)\",95,\"no.\")";
        String q2 = "insert into food values(2,\"Rice(in g)\",1,\"g\")";
        String q3 = "insert into food values(3,\"Chocolate(in g)\",5,\"g\")";
        String q4 = "insert into food values(4,\"Roti(in no.s)\",180,\"no.\")";
        String q5 = "insert into food values(5,\"Sandwich(in no.s)\",204,\"no.\")";
        String q6 = "insert into exercise values(1,\"Walking\",4,\"min\")";
        String q7 = "insert into exercise values(2,\"Jogging\",1,\"min\")";
        String q8 = "insert into exercise values(3,\"Running\",5,\"min\")";
        String q9 = "insert into exercise values(4,\"Swimming\",7,\"min\")";
        String q10 = "insert into exercise values(5,\"Cycling\",11,\"min\")";
        db.execSQL(q1);
        db.execSQL(q2);
        db.execSQL(q3);
        db.execSQL(q4);
        db.execSQL(q5);
        db.execSQL(q6);
        db.execSQL(q7);
        db.execSQL(q8);
        db.execSQL(q9);
        db.execSQL(q10);

        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query1 = "DROP TABLE IF EXISTS "+TABLE_NAME1;
        String query2 = "DROP TABLE IF EXISTS "+TABLE_NAME2;
        String query3 = "DROP TABLE IF EXISTS "+TABLE_NAME3;
        String query4 = "DROP TABLE IF EXISTS "+TABLE_NAME4;
        String query5 = "DROP TABLE IF EXISTS "+TABLE_NAME5;
        String query6 = "DROP TABLE IF EXISTS "+TABLE_NAME6;
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
        this.onCreate(db);
    }
}
