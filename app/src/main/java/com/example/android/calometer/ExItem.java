package com.example.android.calometer;

/**
 * Created by subrafive on 13-09-2017.
 */

public class ExItem {
    int user_id;
    String ex_name;
    int calories,time;

    public int getUserId()
    {
        return user_id;
    }

    public String getExName()
    {
        return ex_name;
    }

    public int getTime()
    {
        return time;

    }

    public int getExCalories()
    {
        return calories;

    }

    public void setExItem(int id,String f,int a,int c)
    {
        user_id = id;
        ex_name = f;
        time = a;
        calories = c;
    }
}
