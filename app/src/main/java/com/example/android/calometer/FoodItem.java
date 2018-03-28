package com.example.android.calometer;

/**
 * Created by subrafive on 06-09-2017.
 */

public class FoodItem {

    int user_id;
    String food_name;
    int calories,amount;

    public int getUserId()
    {
        return user_id;
    }

    public String getFoodName()
    {
        return food_name;
    }

    public int getAmount()
    {
        return amount;

    }

    public int getCalories()
    {
        return calories;

    }

    public void setFoodItem(int id,String f,int a,int c)
    {
        user_id = id;
        food_name = f;
        amount = a;
        calories = c;
    }



}
