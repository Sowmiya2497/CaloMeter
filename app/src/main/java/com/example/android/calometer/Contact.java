package com.example.android.calometer;

/**
 * Created by subrafive on 02-09-2017.
 */

public class Contact {

    int id;
    String username,password;

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    public void setName(String name)
    {
        username = name;
    }

    public String getName()
    {
        return this.username;
    }

    public void setPassword(String pass)
    {
        password = pass;
    }

    public String getPassword()
    {
        return this.password;
    }
}
