package com.example.android.calometer;
/**
 * Created by subrafive on 02-09-2017.
 */

public class target_details {
    int user_id;
    int current_wt, target_wt, target_days, calories_per_day;
    String start_date;

    public void setUser_id(int id){
        this.user_id = id;
    }

    public int getUser_id(){
        return this.user_id;
    }

    public void setCurrent_wt(int cwt){
        this.current_wt = cwt;
    }

    public int getCurrent_wt(){
        return this.current_wt;
    }

    public void setTarget_wt(int twt){
        this.target_wt = twt;
    }

    public int getTarget_wt(){
        return this.target_wt;
    }

    public void setTarget_days(int td){
        this.target_days = td;
    }

    public int getTarget_days(){
        return this.target_days;
    }

    public void setCalories_per_day(int cal){
        this.calories_per_day= cal;
    }

    public int getCalories_per_day(){
        return this.calories_per_day;
    }

    public void setStartDate(String date){
        this.start_date= date;
    }

    public String getStartDate(){
        return this.start_date;
    }
}
