package com.example.android.calometer;

/**
 * Created by subrafive on 05-09-2017.
 */

public class Food {

    int food_id;
    String food_name;
    float calories;

    public String getFood_name()
    {
        return food_name;
    }

    public float getCalories()
    {
        return calories;
    }

    public void setFood_name(String name)
    {
        food_name = name;
    }

    public void setCalories(int cals)
    {
        calories = cals;
    }

}
